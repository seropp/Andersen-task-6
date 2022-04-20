package com.example.contacts.domain.use_cases

import com.example.contacts.domain.models.ContactDomain
import com.example.contacts.domain.repository.ContactRepository

class UpdateContactUseCase(
    private val contactRepository: ContactRepository
) {

    suspend fun execute(contactDomain: ContactDomain) {
        return contactRepository.updateContact(contactDomain = contactDomain)
    }
}