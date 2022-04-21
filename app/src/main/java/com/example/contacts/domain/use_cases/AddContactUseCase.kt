package com.example.contacts.domain.use_cases

import com.example.contacts.domain.models.ContactDomain
import com.example.contacts.domain.repository.ContactRepository

class AddContactUseCase(
    private val contactRepository: ContactRepository
) {

    suspend fun execute(contactDomain: ContactDomain) {
        val firstName = contactDomain.firstName ?: ""
        val lastName = contactDomain.lastName ?: ""
        val phoneNumber = contactDomain.phoneNumber ?: ""
        contactRepository.addContact(
            contactDomain = ContactDomain(
                firstName = firstName,
                lastName = lastName,
                phoneNumber = phoneNumber,
                id = null
            )
        )
    }
}