package com.example.contacts.presentation.contacts_list_adapter

import com.example.contacts.presentation.models.Contact

interface ContactsListener {

    fun onItemClick(contact: Contact)

    fun onLongItemClick(id: Int?): Boolean
}