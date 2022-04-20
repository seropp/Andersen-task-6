package com.example.contacts.presentation.contactc_info_fragment

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.contacts.data.repository.ContactRepositoryImpl
import com.example.contacts.data.storage.local.ContactsDatabase
import com.example.contacts.domain.use_cases.AddContactUseCase
import com.example.contacts.domain.use_cases.UpdateContactUseCase

class ContactInfoViewModelFactory(
    context: Context
) : ViewModelProvider.Factory {

    private val db by lazy {
        ContactsDatabase(context = context)
    }

    private val contactRepository by lazy {
        ContactRepositoryImpl(db = db)
    }

    private val updateContactUseCase by lazy {
        UpdateContactUseCase(contactRepository = contactRepository)
    }

    private val addContactUseCase by lazy {
        AddContactUseCase(contactRepository = contactRepository)
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ContactInfoViewModel(
            addContactUseCase = addContactUseCase,
            updateContactUseCase = updateContactUseCase
        ) as T
    }
}