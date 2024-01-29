package com.example.simon.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.simon.R
import com.example.simon.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding // Data binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Recupere le bouton
        val button = binding.playBtn

        // positionner un OnClickListener sur le bouton
        /* Cette methode est identique à celle du dessous
        button.setOnClickListener(object: View.OnClickListener {
            override fun onClick(p0: View?) {
                // Au clic, appeler le navController et déclencher l'action
            }
        })*/

        button.setOnClickListener {
            // Au clic, appeler le navController et déclencher l'action
            findNavController().navigate(R.id.action_homeFragment2_to_hardModeFragment)
        }
    }
}