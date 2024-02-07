package com.example.simon.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.simon.R
import com.example.simon.SimonGame
import com.example.simon.databinding.FragmentMediumModeBinding


class MediumModeFragment : Fragment() {

    private lateinit var binding: FragmentMediumModeBinding // Data binding
    private var score = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentMediumModeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val homeButton = view.findViewById<Button>(R.id.homeBtn)
        homeButton.setOnClickListener {
            findNavController().navigate(R.id.homeFragment2)
        }

        val lifecycleScope = viewLifecycleOwner.lifecycleScope
        val btnIds = listOf(R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4,R.id.btn5,R.id.btn6,R.id.btn7,R.id.btn8,R.id.btn9)
        val scoreText = view.findViewById<TextView>(R.id.scoreText)

        val simonGame = SimonGame(lifecycleScope,
            scoreText,
            this,
            view,
            btnIds)
        simonGame.playGame()
    }
}


