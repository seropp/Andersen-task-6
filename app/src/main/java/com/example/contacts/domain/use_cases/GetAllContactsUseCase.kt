package com.example.contacts.domain.use_cases

import com.example.contacts.domain.models.ContactDomain
import com.example.contacts.domain.repository.ContactRepository

class GetAllContactsUseCase(
    private val contactRepository: ContactRepository
) {

    suspend fun execute(): List<ContactDomain>? {
        return contactRepository.getContacts()
    }
}