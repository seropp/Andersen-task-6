package com.example.contacts.domain.repository

import androidx.lifecycle.LiveData
import androidx.room.PrimaryKey
import com.example.contacts.domain.models.Contact

interface ContactRepository {

    suspend fun addContact(contact: Contact)

    suspend fun deleteContactById(id: Int?)

    suspend fun getContact(id: Int?): Contact

    suspend fun updateContact(contact: Contact)

    fun getContacts(): LiveData<List<Contact>>
}