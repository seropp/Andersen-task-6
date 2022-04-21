package com.example.contacts.presentation

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.contacts.R
import com.example.contacts.databinding.ActivityContactsBinding
import com.example.contacts.presentation.contactc_info_fragment.ContactInfoFragment
import com.example.contacts.presentation.contacts_list_fragment.ContactsListFragment
import com.example.contacts.presentation.models.Contact

class ContactsActivity : AppCompatActivity(), ContactsNavigator {

    private lateinit var binding: ActivityContactsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        addFirstFragment()
        binding = ActivityContactsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun launchInfoFragment(
        contact: Contact?
    ) {
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.container,
                ContactInfoFragment.newInstance(
                    contact = contact,
                ),
                "SECOND_THREAD"
            ).addToBackStack("STACK")
            .commit()
    }

    override fun launchContactsListFragment() {
        addFirstFragment()
    }

    private fun addFirstFragment(){
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, ContactsListFragment(), "MAIN_FRAGMENT")
            .addToBackStack("MAIN_FRAGMENT")
            .commit()
    }

}