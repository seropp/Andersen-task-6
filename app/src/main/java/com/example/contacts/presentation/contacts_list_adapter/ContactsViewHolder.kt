package com.example.contacts.presentation.contacts_list_adapter


import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.example.contacts.R
import com.example.contacts.databinding.ContactItemBinding
import com.example.contacts.presentation.models.Contact
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class ContactsViewHolder(
    itemView: View,
    private val listener: ContactsListener,
) : RecyclerView.ViewHolder(itemView) {

    private val binding = ContactItemBinding.bind(itemView)

    fun onBind(contact: Contact) = with(binding) {
        firstNameItem.text = contact.firstName
        lastNameItem.text = contact.lastName
        phoneNumberItem.text = contact.phoneNumber

        CoroutineScope(context = Dispatchers.IO).launch {
            contactImage.load("https://picsum.photos/200/300?random=${(contact.id?.times(2))}") {
                crossfade(true)
                placeholder(R.drawable.ic_launcher_foreground)
                transformations(CircleCropTransformation())
            }
        }

        card.setOnClickListener {
            listener.onItemClick(contact = contact)
        }

        card.setOnLongClickListener {
            listener.onLongItemClick(id = contact.id)
        }

    }
}