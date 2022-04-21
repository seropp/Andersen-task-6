package com.example.contacts.data.storage.local

import androidx.room.*
import com.example.contacts.data.storage.local.entity.ContactEntity

@Dao
interface ContactDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertContact(contactEntity: ContactEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllContacts(contactsList: List<ContactEntity>)

    @Query("DELETE FROM contacts_info WHERE id = :contact_id")
    suspend fun deleteContactById(contact_id: Int?)

    @Query("SELECT * FROM contacts_info WHERE id = :contact_id")
    suspend fun getContactById(contact_id: Int?): ContactEntity

    @Update
    suspend fun updateContact(contactEntity: ContactEntity)

    @Query("SELECT * FROM contacts_info")
    suspend fun getAllContacts(): List<ContactEntity>

    @Query("SELECT * FROM contacts_info WHERE first_name LIKE :searchQuery OR last_name LIKE :searchQuery")
    suspend fun searchDatabase(searchQuery: String): List<ContactEntity>?
}