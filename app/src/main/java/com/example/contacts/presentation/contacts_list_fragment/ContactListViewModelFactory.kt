package com.example.contacts.presentation.contacts_list_fragment

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.contacts.data.repository.ContactRepositoryImpl
import com.example.contacts.data.storage.local.ContactsDatabase
import com.example.contacts.domain.use_cases.*

class ContactListViewModelFactory(
    context: Context
) : ViewModelProvider.Factory {

    private val db by lazy {
        ContactsDatabase(context = context)
    }

    private val contactRepository by lazy {
        ContactRepositoryImpl(db = db)
    }

    private val deleteContactUseCase by lazy {
        DeleteContactUseCase(contactRepository = contactRepository)
    }

    private val getAllContactsUseCase by lazy {
        GetAllContactsUseCase(contactRepository = contactRepository)
    }

    private val searchContactsUseCase by lazy {
        SearchContactsUseCase(contactRepository = contactRepository)
    }

    private val addAllContactsUseCase by lazy {
        AddAllContactsUseCase(contactRepository = contactRepository)
    }

    private val checkRoomUseCase by lazy {
        CheckRoomUseCase(contactRepository = contactRepository)
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ContactsListViewModel(
            getAllContactsUseCase = getAllContactsUseCase,
            deleteContactUseCase = deleteContactUseCase,
            searchContactsUseCase = searchContactsUseCase,
            addAllContactsUseCase = addAllContactsUseCase,
            checkRoomUseCase = checkRoomUseCase
        ) as T
    }
}