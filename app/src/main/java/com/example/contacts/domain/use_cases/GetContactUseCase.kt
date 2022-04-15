package com.example.contacts.domain.use_cases

import com.example.contacts.domain.models.Contact
import com.example.contacts.domain.repository.ContactRepository

class GetContactUseCase(
    private val contactRepository: ContactRepository
) {

    suspend fun execute(id: Int?): Contact {
        return contactRepository.getContact(id = id)
    }
}