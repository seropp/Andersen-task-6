package com.example.contacts.data.repository

import androidx.lifecycle.LiveData
import com.example.contacts.domain.models.Contact
import com.example.contacts.domain.repository.ContactRepository

class ContactRepositoryImpl() : ContactRepository {
    override suspend fun addContact(contact: Contact) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteContactById(id: Int?) {
        TODO("Not yet implemented")
    }

    override suspend fun getContact(id: Int?): Contact {
        TODO("Not yet implemented")
    }

    override suspend fun updateContact(contact: Contact) {
        TODO("Not yet implemented")
    }

    override fun getContacts(): LiveData<List<Contact>> {
        TODO("Not yet implemented")
    }

}