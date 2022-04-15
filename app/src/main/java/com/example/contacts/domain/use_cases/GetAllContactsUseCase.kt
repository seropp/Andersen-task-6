package com.example.contacts.domain.use_cases

import androidx.lifecycle.LiveData
import com.example.contacts.domain.models.Contact
import com.example.contacts.domain.repository.ContactRepository

class GetAllContactsUseCase(
    private val contactRepository: ContactRepository
) {

    fun execute(): LiveData<List<Contact>> {
        return contactRepository.getContacts()
    }
}