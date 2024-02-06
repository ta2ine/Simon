package com.example.simon.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.simon.R
import com.example.simon.databinding.FragmentMediumModeBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

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
        super.onViewCreated(view, savedInstanceState)

        val button = view.findViewById<Button>(R.id.homeBtn)
        val btn1 = view.findViewById<Button>(R.id.btn1)
        val btn2 = view.findViewById<Button>(R.id.btn2)
        val btn3 = view.findViewById<Button>(R.id.btn3)
        val btn4 = view.findViewById<Button>(R.id.btn4)
        val btn5 = view.findViewById<Button>(R.id.btn5)
        val btn6 = view.findViewById<Button>(R.id.btn6)
        val btn7 = view.findViewById<Button>(R.id.btn7)
        val btn8 = view.findViewById<Button>(R.id.btn8)
        val btn9 = view.findViewById<Button>(R.id.btn9)


        btn1.setBackgroundResource(R.drawable.btn_normal)
        btn2.setBackgroundResource(R.drawable.btn_normal)
        btn3.setBackgroundResource(R.drawable.btn_normal)
        btn4.setBackgroundResource(R.drawable.btn_normal)
        btn5.setBackgroundResource(R.drawable.btn_normal)
        btn6.setBackgroundResource(R.drawable.btn_normal)
        btn7.setBackgroundResource(R.drawable.btn_normal)
        btn8.setBackgroundResource(R.drawable.btn_normal)
        btn9.setBackgroundResource(R.drawable.btn_normal)


        button.setOnClickListener {
            findNavController().navigate(R.id.action_mediumModeFragment_to_homeFragment2)
        }

        val sequenceClient: MutableList<Button> = mutableListOf()

        btn1.setOnClickListener {
            addSelfButtonToSequenceClient(sequenceClient, btn1)
        }
        btn2.setOnClickListener {
            addSelfButtonToSequenceClient(sequenceClient, btn2)
        }
        btn3.setOnClickListener {
            addSelfButtonToSequenceClient(sequenceClient, btn3)
        }
        btn4.setOnClickListener {
            addSelfButtonToSequenceClient(sequenceClient, btn4)
        }
        btn5.setOnClickListener {
            addSelfButtonToSequenceClient(sequenceClient, btn5)
        }
        btn6.setOnClickListener {
            addSelfButtonToSequenceClient(sequenceClient, btn6)
        }
        btn7.setOnClickListener {
            addSelfButtonToSequenceClient(sequenceClient, btn7)
        }
        btn8.setOnClickListener {
            addSelfButtonToSequenceClient(sequenceClient, btn8)
        }
        btn9.setOnClickListener {
            addSelfButtonToSequenceClient(sequenceClient, btn9)
        }



        val sequenceGame = mutableListOf(getRandomBtn(btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9))
        updateTestText("SCORE : 0")
        lightOnOffBtn(sequenceGame)
        startCheckingSequence(sequenceGame, sequenceClient, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9)
    }

    private fun updateTestText(text: String) {
        binding.testText.text = text
    }

    private fun lightOnBtn(button: Button) {
        button.setBackgroundResource(R.drawable.btn_to_click)
    }

    private fun lightOffBtn(button: Button) {
        button.setBackgroundResource(R.drawable.btn_normal)
    }

    private fun lightOnOffBtn(buttonList: MutableList<Button>) {
        lifecycleScope.launch {
            for (button in buttonList) {
                lightOnBtn(button)
                delay(1000) // Attendre 1 seconde
                lightOffBtn(button)
            }
        }
    }

    private fun clearSequence(buttonList: MutableList<Button>) {
        buttonList.clear()
    }

    private fun addSelfButtonToSequenceClient(sequenceClient: MutableList<Button>, button: Button) {
        sequenceClient.add(button)
        val sequenceText = sequenceClient.joinToString(", ") { it.id.toString() }
        Log.d("SequenceClient", "${button.id} ajouté à la séquence : $sequenceText")
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    private fun getRandomBtn(btn1: Button, btn2: Button, btn3: Button, btn4: Button, btn5: Button, btn6: Button, btn7: Button, btn8: Button, btn9: Button): Button {
        val random = Random.nextInt(4) + 1
        return when (random) {
            1 -> btn1
            2 -> btn2
            3 -> btn3
            4 -> btn4
            5 -> btn5
            6 -> btn6
            7 -> btn7
            8 -> btn8
            else -> btn9
        }
    }

    private fun addRandomBtnTosequence(buttonList: MutableList<Button>, btn1: Button, btn2: Button, btn3: Button, btn4: Button, btn5: Button, btn6: Button, btn7: Button, btn8: Button, btn9: Button) {
        var newButton: Button
        do {
            newButton = getRandomBtn(btn1, btn2, btn3, btn4,btn5, btn6, btn7, btn8, btn9)
        } while (newButton == buttonList.last())

        buttonList.add(newButton)

        val sequenceText = buttonList.joinToString(", ") { it.id.toString() }
        Log.d("SequenceClient", "${newButton.id} ajouté à la séquence : $sequenceText")
    }

    private fun startCheckingSequence(sequenceGame: MutableList<Button>, sequenceClient: MutableList<Button>, btn1: Button, btn2: Button, btn3: Button, btn4: Button, btn5: Button, btn6: Button, btn7: Button, btn8: Button, btn9: Button) {
        lifecycleScope.launch {
            while (true) {
                delay(1000) // Vérifier la séquence chaque seconde (ajustez selon vos besoins)

                val result = checkSequence(sequenceGame, sequenceClient, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9)
                if (!result) {
                    findNavController().navigate(R.id.action_mediumModeFragment_to_resultFragment)
                    break
                }
            }
        }
    }

    private fun checkSequence(sequenceGame: MutableList<Button>, sequenceClient: MutableList<Button>, btn1: Button, btn2: Button, btn3: Button, btn4: Button, btn5: Button, btn6: Button, btn7: Button, btn8: Button, btn9: Button): Boolean {
        val minSize = minOf(sequenceGame.size, sequenceClient.size)

        for (i in 0 until minSize) {
            val buttonGame = sequenceGame[i]
            val buttonClient = sequenceClient[i]

            if (buttonGame != buttonClient) {
                // Réinitialiser les séquences
                return false
            }
        }

        // success
        if (sequenceGame.size == sequenceClient.size) {
            // La séquence client correspond entièrement à la séquence du jeu
            score = incrementScore(score)
            updateTestText("SCORE : ${score.toString()}")

            // Réinitialiser la séquence client
            clearSequence(sequenceClient)
            // Ajouter un nouveau bouton à la séquence du jeu
            addRandomBtnTosequence(sequenceGame, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9)
            // Continuer le jeu
            lightOnOffBtn(sequenceGame)
        }
        return true
    }

    private fun incrementScore(score: Int): Int {
        return score + 1
    }
}
