package com.example.simon.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.simon.R
import com.example.simon.databinding.FragmentMediumModeBinding

class MediumModeFragment : Fragment() {

    private lateinit var binding: FragmentMediumModeBinding // Data binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentMediumModeBinding .inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val button = view.findViewById<Button>(R.id.homeBtn)

        button.setOnClickListener {
            findNavController().navigate(R.id.action_EasyMode_to_homeFragment2)
        }
    }
}