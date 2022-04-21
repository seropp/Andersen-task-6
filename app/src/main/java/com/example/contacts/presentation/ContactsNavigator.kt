package com.example.contacts.presentation

import androidx.fragment.app.Fragment
import com.example.contacts.presentation.models.Contact

fun Fragment.navigator(): ContactsNavigator {
    return requireActivity() as ContactsNavigator
}

interface ContactsNavigator {

    fun launchInfoFragment(contact: Contact?)

    fun launchContactsListFragment()

}