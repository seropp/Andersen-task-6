package com.example.contacts.domain.use_cases

import com.example.contacts.domain.models.Contact
import com.example.contacts.domain.repository.ContactRepository

class UpdateContactUseCase (
    private val contactRepository: ContactRepository
) {

    suspend fun execute(contact: Contact){
        return contactRepository.updateContact(contact = contact)
    }
}