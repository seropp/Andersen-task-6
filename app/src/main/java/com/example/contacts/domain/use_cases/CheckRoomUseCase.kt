package com.example.contacts.domain.use_cases

import com.example.contacts.domain.repository.ContactRepository

class CheckRoomUseCase(
    private val contactRepository: ContactRepository
) {

    suspend fun execute(): Boolean {
        return contactRepository.checkRoom()
    }
}