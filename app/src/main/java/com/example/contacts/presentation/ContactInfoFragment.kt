package com.example.contacts.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.contacts.databinding.FragmentContactInfoBinding

class ContactInfoFragment : Fragment() {

    private lateinit var binding: FragmentContactInfoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentContactInfoBinding =
            FragmentContactInfoBinding.inflate(inflater, container, false)



        return binding.root
    }

}