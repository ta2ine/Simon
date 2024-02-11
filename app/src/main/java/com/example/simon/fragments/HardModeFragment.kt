package com.example.simon.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.simon.R
import com.example.simon.SimonGame
import com.example.simon.databinding.FragmentHardModeBinding

class HardModeFragment : Fragment(), SimonGame.GameEventListener {

    private lateinit var binding: FragmentHardModeBinding // Data binding
    private lateinit var navController: NavController


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHardModeBinding .inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val button = view.findViewById<Button>(R.id.homeBtn)
        button.setOnClickListener {
            findNavController().navigate(R.id.action_hardModeFragment_to_homeFragment2)
        }
        navController = findNavController()

        val lifecycleScope = viewLifecycleOwner.lifecycleScope
        val btnIds = listOf(R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4,R.id.btn5,R.id.btn6,R.id.btn7,R.id.btn8,R.id.btn9,R.id.btn10,R.id.btn11,R.id.btn12,R.id.btn13,R.id.btn14,R.id.btn15,R.id.btn16)
        val scoreText = view.findViewById<TextView>(R.id.scoreText)

        val simonGame = SimonGame(lifecycleScope,
            scoreText,
            this,
            view,
            btnIds,
            this)
        simonGame.playGame()
    }

    override fun onGameEnd(score: Int) {
        navigateToResultFragment(score, "Hard")
    }

    private fun navigateToResultFragment(score: Int, level:String) {
        val action = HardModeFragmentDirections.actionHardModeFragmentToResultFragment(score, level)
        navController.navigate(action)
    }
}