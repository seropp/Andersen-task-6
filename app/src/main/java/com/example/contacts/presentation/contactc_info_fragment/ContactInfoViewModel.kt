package com.example.contacts.presentation.contactc_info_fragment

import com.example.contacts.domain.models.ContactDomain
import com.example.contacts.domain.use_cases.AddContactUseCase
import com.example.contacts.domain.use_cases.UpdateContactUseCase
import com.example.contacts.presentation.models.Contact
import kotlinx.coroutines.launch
import java.lang.Exception
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

class ContactInfoViewModel(
    private val addContactUseCase: AddContactUseCase,
    private val updateContactUseCase: UpdateContactUseCase,
) : ViewModel() {

    fun save(firstName: String?, lastName: String?, phone: String?, id: Int?) {

        try {
            viewModelScope.launch {
                val contact = Contact(
                    firstName = firstName,
                    lastName = lastName,
                    phoneNumber = phone,
                    id = id
                )
                addContactUseCase.execute(contactDomain = mapToContactDomain(contact = contact))
            }
        } catch (e: Exception) {
        }
    }

    fun update(firstName: String?, lastName: String?, phone: String?, id: Int?) {
        val contact = Contact(
            firstName = firstName,
            lastName = lastName,
            phoneNumber = phone,
            id = id
        )
        try {
            viewModelScope.launch {
                updateContactUseCase.execute(mapToContactDomain(contact))
            }
        } catch (e: Exception) {
        }
    }

    private fun mapToContactDomain(contact: Contact): ContactDomain {
        return ContactDomain(
            firstName = contact.firstName,
            lastName = contact.lastName,
            phoneNumber = contact.phoneNumber,
            id = contact.id
        )
    }
}