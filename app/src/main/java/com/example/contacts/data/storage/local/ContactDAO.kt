package com.example.contacts.data.storage.local


import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.contacts.data.storage.local.entity.ContactEntity
import java.util.concurrent.Flow

@Dao
interface ContactDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertContact(contactEntity: ContactEntity)

    @Query("DELETE FROM `Contacts info` WHERE id = :contact_id")
    suspend fun deleteContactById(contact_id: Int?)

    @Query("SELECT * FROM `Contacts info` WHERE id = :contact_id")
    suspend fun getContactById(contact_id: Int?)

    @Update
    suspend fun updateContact(contactEntity: ContactEntity)

    @Query("SELECT * FROM `Contacts info`")
    fun getAllContacts(): LiveData<List<ContactEntity>>
}