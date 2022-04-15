package com.example.contacts.domain.use_cases

import com.example.contacts.domain.models.Contact
import com.example.contacts.domain.repository.ContactRepository

class AddContactUseCase(
    private val contactRepository: ContactRepository
) {

    suspend fun execute(contact: Contact) {
        val firstName = contact.firstName ?: ""
        val lastName = contact.lastName ?: ""
        val phoneNumber = contact.phoneNumber ?: ""
        contactRepository.addContact(
            contact = Contact(
                firstName = firstName,
                lastName = lastName,
                phoneNumber = phoneNumber,
                id = null
            )
        )
    }
}