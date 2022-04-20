package com.example.contacts.presentation.contactc_info_fragment

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.ViewModelProvider
import coil.load
import coil.transform.CircleCropTransformation
import com.example.contacts.R
import com.example.contacts.databinding.FragmentContactInfoBinding
import com.example.contacts.presentation.models.Contact
import com.example.contacts.presentation.navigator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

private const val KEY_CONTACT = "CONTACT"

class ContactInfoFragment : Fragment() {

    private var binding: FragmentContactInfoBinding? = null
    private var vm: ContactInfoViewModel? = null
    private var contact: Contact? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        requireActivity()
            .onBackPressedDispatcher
            .addCallback(this, object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    if (isEnabled) {
                        isEnabled = false
                        navigator().launchContactsListFragment()
                    }
                }
            })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentContactInfoBinding.inflate(inflater, container, false)

        vm = ViewModelProvider(
            this,
            ContactInfoViewModelFactory(requireContext())
        )[ContactInfoViewModel::class.java]

        saveOrUpdateContact()
        init()
        return binding!!.root
    }

    private fun init() {
        arguments?.let {
            contact = it.getParcelable(KEY_CONTACT)
        }
        binding!!.contactFirstName.text = contact?.firstName
        binding!!.contactLastName.text = contact?.lastName
        binding!!.contactPhoneNumber.text = contact?.phoneNumber

    }

    private fun saveOrUpdateContact() {
        binding!!.btnSave.setOnClickListener {

            if (contact == null) {

                vm!!.save(
                    firstName = binding!!.etFirstNameContact.text.toString(),
                    lastName = binding!!.etLastNameContact.text.toString(),
                    phone = binding!!.etPhoneNumberContact.text.toString(),
                    id = null
                )
                navigator().launchContactsListFragment()
            } else {
                val firstName = binding!!.etFirstNameContact.text.toString().ifEmpty { contact!!.firstName }
                val lastName = binding!!.etLastNameContact.text.toString().ifEmpty { contact!!.lastName }
                val phoneNumber = binding!!.etPhoneNumberContact.text.toString().ifEmpty { contact!!.phoneNumber }

                vm!!.update(
                    firstName = firstName,
                    lastName = lastName,
                    phone = phoneNumber,
                    id = contact!!.id
                )
                navigator().launchContactsListFragment()
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(contact: Contact?) =
            ContactInfoFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(KEY_CONTACT, contact)
                }
            }
    }
}