package com.example.contacts.domain.use_cases

import com.example.contacts.domain.models.ContactDomain
import com.example.contacts.domain.repository.ContactRepository

class SearchContactsUseCase(
    private val contactRepository: ContactRepository
) {

    suspend fun execute(searchQuery: String): List<ContactDomain>? {
        return contactRepository.searchContacts(searchQuery = searchQuery)
    }
}