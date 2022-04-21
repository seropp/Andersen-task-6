package com.example.contacts.presentation.contacts_list_adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.contacts.R
import com.example.contacts.presentation.models.Contact

class ContactsAdapter(
    var listener: ContactsListener
) : ListAdapter<Contact, ContactsViewHolder>(ContactsDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactsViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.contact_item, parent, false)

        return ContactsViewHolder(itemView = itemView, listener = listener)
    }

    override fun onBindViewHolder(holderContacts: ContactsViewHolder, position: Int) {
        holderContacts.onBind(getItem(position))
    }

    private class ContactsDiffCallback : DiffUtil.ItemCallback<Contact>() {

        override fun areItemsTheSame(oldItem: Contact, newItem: Contact): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Contact, newItem: Contact): Boolean {
            return oldItem == newItem
        }
    }
}