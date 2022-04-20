package com.example.contacts.domain.use_cases

import com.example.contacts.domain.models.ContactDomain
import com.example.contacts.domain.repository.ContactRepository

class AddAllContactsUseCase(private val contactRepository: ContactRepository) {

    suspend fun execute(contactsList: List<ContactDomain>) {
        contactRepository.addAllContacts(contactsList)
    }
}