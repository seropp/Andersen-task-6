package com.example.contacts.domain.use_cases

import com.example.contacts.domain.models.Contact
import com.example.contacts.domain.repository.ContactRepository

class DeleteContactUseCase(
    private val contactRepository: ContactRepository
) {

    suspend fun execute(id: Int?) {
        contactRepository.deleteContactById(id = id)
    }
}

