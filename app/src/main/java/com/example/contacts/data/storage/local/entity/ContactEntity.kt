package com.example.contacts.data.storage.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contacts_info")
data class ContactEntity(
    @ColumnInfo(name = "first_name")
    val firstName: String?,
    @ColumnInfo(name = "last_name")
    val lastName: String?,
    @ColumnInfo(name = "phone_number")
    val phoneNumber: String?,
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
)