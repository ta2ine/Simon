package com.example.simon.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.simon.R
import com.example.simon.databinding.FragmentResultBinding

class ResultFragment : Fragment() {

    private lateinit var binding: FragmentResultBinding // Data binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentResultBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val homeBtn = view.findViewById<Button>(R.id.homeBtn)
        homeBtn.setOnClickListener {
            findNavController().navigate(R.id.action_resultFragment_to_homeFragment2)
        }

        val score = arguments?.getInt("score", 0)
        val scoreText = view.findViewById<TextView>(R.id.scoreText)
        scoreText.text = "Score: $score"

    }
}