package com.example.contacts.presentation.contacts_list_fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.contacts.domain.models.ContactDomain
import com.example.contacts.domain.use_cases.*
import com.example.contacts.presentation.contacts_list_adapter.ContactsListener
import com.example.contacts.presentation.models.Contact
import com.github.javafaker.Faker
import kotlinx.coroutines.launch
import java.lang.Exception
import java.util.*

class ContactsListViewModel(
    private val getAllContactsUseCase: GetAllContactsUseCase,
    private val deleteContactUseCase: DeleteContactUseCase,
    private val searchContactsUseCase: SearchContactsUseCase,
    private val addAllContactsUseCase: AddAllContactsUseCase,
    private val checkRoomUseCase: CheckRoomUseCase
) : ViewModel(), ContactsListener {

    var listContacts: MutableLiveData<List<Contact>?> = MutableLiveData<List<Contact>?>()
    var alertDialogLiveData = MutableLiveData<Int?>()
    var transferContact = MutableLiveData<Contact?>()

    fun checkRoom() {
        viewModelScope.launch {
            if (checkRoomUseCase.execute()) {
                addAllContactsUseCase.execute(contactsList = mapToListContactsDomain(generateRandomContacts()))
                listContacts.value = mapToListContact(getAllContactsUseCase.execute())
            }
        }
    }

    fun getContactsList() {
        viewModelScope.launch {
            listContacts.value = mapToListContact(getAllContactsUseCase.execute())
        }
    }

    fun searchContacts(searchQuery: String) {
        viewModelScope.launch {
            listContacts.value =
                mapToListContact(searchContactsUseCase.execute(searchQuery = searchQuery))
        }
    }

    fun getTransferContact(): LiveData<Contact?> {
        return transferContact
    }

    override fun onItemClick(contact: Contact) {
        transferContact.value = contact
    }

    override fun onLongItemClick(id: Int?): Boolean {
        alertDialogLiveData.value = id
        return true
    }

    fun deleteContact(id: Int?) {
        try {
            viewModelScope.launch {
                deleteContactUseCase.execute(id = id)
                getContactsList()
            }
        } catch (e: Exception) {
        }
    }

    private fun mapToListContact(allContacts: List<ContactDomain>?): List<Contact>? {
        return allContacts?.map { mapToContact(it) }
    }

    private fun mapToListContactsDomain(allContacts: List<Contact>): List<ContactDomain> {
        return allContacts.map { mapToContactDomain(it) }
    }

    private fun mapToContact(contactDomain: ContactDomain): Contact {
        return Contact(
            firstName = contactDomain.firstName,
            lastName = contactDomain.lastName,
            phoneNumber = contactDomain.phoneNumber,
            id = contactDomain.id
        )
    }

    private fun mapToContactDomain(contact: Contact): ContactDomain {
        return ContactDomain(
            firstName = contact.firstName,
            lastName = contact.lastName,
            phoneNumber = contact.phoneNumber,
            id = contact.id
        )
    }

    private fun generateRandomContacts(): List<Contact> {
        val list = mutableListOf<Contact>()
        val faker = Faker(Locale("ru"))
        for (i in 1..200) {
            list.add(
                Contact(
                    firstName = faker.name().firstName(),
                    lastName = faker.name().lastName(),
                    phoneNumber = faker.phoneNumber().phoneNumber(),
                    id = i
                )
            )
        }
        return list
    }
}