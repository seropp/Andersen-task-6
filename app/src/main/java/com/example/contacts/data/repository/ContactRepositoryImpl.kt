package com.example.contacts.data.repository

import com.example.contacts.data.storage.local.entity.ContactEntity
import com.example.contacts.data.storage.local.ContactsDatabase
import com.example.contacts.domain.models.ContactDomain
import com.example.contacts.domain.repository.ContactRepository

class ContactRepositoryImpl(
    private val db: ContactsDatabase
) : ContactRepository {

    override suspend fun addContact(contactDomain: ContactDomain) =
        db.getContactDao().insertContact(mapToContactEntity(contactDomain = contactDomain))

    override suspend fun deleteContactById(id: Int?) =
        db.getContactDao().deleteContactById(contact_id = id)

    override suspend fun updateContact(contactDomain: ContactDomain) =
        db.getContactDao().updateContact(mapToContactEntity(contactDomain = contactDomain))

    override suspend fun getContacts(): List<ContactDomain>? =
        mapToListContact(db.getContactDao().getAllContacts())

    override suspend fun searchContacts(searchQuery: String): List<ContactDomain>? =
        mapToListContact(db.getContactDao().searchDatabase(searchQuery = searchQuery))

    override suspend fun addAllContacts(contactsList: List<ContactDomain>) =
        db.getContactDao().insertAllContacts(mapToListContactEntity(contactsList))

    override suspend fun checkRoom(): Boolean =
        db.getContactDao().getAllContacts().isEmpty()

    private fun mapToContactEntity(contactDomain: ContactDomain): ContactEntity {
        return ContactEntity(
            firstName = contactDomain.firstName,
            lastName = contactDomain.lastName,
            phoneNumber = contactDomain.phoneNumber,
            id = contactDomain.id
        )
    }

    private fun mapToContact(contactEntity: ContactEntity): ContactDomain {
        return ContactDomain(
            firstName = contactEntity.firstName,
            lastName = contactEntity.lastName,
            phoneNumber = contactEntity.phoneNumber,
            id = contactEntity.id
        )
    }

    private fun mapToListContact(contactEntityList: List<ContactEntity>?): List<ContactDomain>? {
        return (contactEntityList)?.map { mapToContact(it) }
    }

    private fun mapToListContactEntity(contactDomain: List<ContactDomain>): List<ContactEntity> {
        return (contactDomain)?.map { mapToContactEntity(it) }
    }
}