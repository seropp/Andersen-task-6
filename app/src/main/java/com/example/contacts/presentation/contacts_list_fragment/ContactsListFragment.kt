package com.example.contacts.presentation.contacts_list_fragment

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.contacts.databinding.FragmentContactsListBinding
import com.example.contacts.presentation.contacts_list_adapter.ContactsAdapter
import com.example.contacts.presentation.navigator
import android.view.*
import androidx.activity.OnBackPressedCallback
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.contacts.R

class ContactsListFragment : Fragment(), androidx.appcompat.widget.SearchView.OnQueryTextListener {

    private var binding: FragmentContactsListBinding? = null
    private var vm: ContactsListViewModel? = null
    private var contactsAdapter: ContactsAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requireActivity()
            .onBackPressedDispatcher
            .addCallback(this, object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    if (isEnabled) {
                        isEnabled = false
                        requireActivity().finish()
                    }
                }
            })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentContactsListBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)

        vm = ViewModelProvider(
            this,
            ContactListViewModelFactory(requireContext())
        )[ContactsListViewModel::class.java]

        vm!!.checkRoom()
        vm!!.getContactsList()



        contactsAdapter = ContactsAdapter(listener = vm!!)

        binding!!.addItem.setOnClickListener {
            navigator().launchInfoFragment(
                contact = null
            )
        }

        observingVM()
        init()

        return binding!!.root
    }

    private fun init() {
        binding!!.rvFragmentContacts.apply {
            val linearLayoutManager = LinearLayoutManager(requireContext())
            linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
            layoutManager = linearLayoutManager
            hasFixedSize()
            adapter = this@ContactsListFragment.contactsAdapter
        }
        val dividerItemDecoration = DividerItemDecoration(requireContext(), RecyclerView.VERTICAL)
        ResourcesCompat.getDrawable(resources, R.drawable.divider_drawable, null)
            ?.let { drawable -> dividerItemDecoration.setDrawable(drawable) }
        binding!!.rvFragmentContacts.addItemDecoration(dividerItemDecoration)
    }


    private fun observingVM() {
        vm!!.listContacts.observe(viewLifecycleOwner, Observer { it ->
            val sortedList = it?.sortedWith(compareBy({ it.lastName }, { it.firstName }))
            contactsAdapter!!.submitList(sortedList)
        })

        vm!!.alertDialogLiveData.observe(viewLifecycleOwner, Observer {

            AlertDialog.Builder(requireContext())
                .setMessage("Delete contact?")
                .setPositiveButton("Yes") { _, _ -> vm!!.deleteContact(it) }
                .setNegativeButton("No") { _, _ -> }
                .show()
        })

        vm!!.getTransferContact().observe(viewLifecycleOwner, Observer {
            navigator().launchInfoFragment(
                contact = it
            )
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
        val search = menu.findItem(R.id.menu_search)
        val searchView = search.actionView as? androidx.appcompat.widget.SearchView
        searchView?.isSubmitButtonEnabled = true
        searchView?.setOnQueryTextListener(this)
    }

    override fun onQueryTextSubmit(p0: String?): Boolean {
        if (p0 != null) {
            searchDatabase(query = p0)
        }
        return true
    }

    override fun onQueryTextChange(p0: String?): Boolean {
        if (p0 != null) {
            searchDatabase(query = p0)
        }
        return true
    }

    private fun searchDatabase(query: String) {
        val searchQuery = "%$query%"
        vm!!.searchContacts(searchQuery = searchQuery)
    }

}