package com.example.simon

import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

class SimonGame(private val lifecycleScope: LifecycleCoroutineScope,
                private val bindingClass: Class<*>,
                private val textView: TextView,
                private val fragment: Fragment,
                private val view: View,
                private val btnIds: List<Int>) {

    private var score = 0
    val btns = mutableListOf<Button>()

    fun playGame() {
        val sequenceClient: MutableList<Button> = mutableListOf()

        btnIds.forEach { btnId ->
            val btn = view.findViewById<Button>(btnId)
            btn.setOnClickListener {
                addSelfButtonToSequenceClient(sequenceClient, btn)
            }
            btns.add(btn)
        }

        val sequenceGame = mutableListOf(getRandomBtn(btns))
        setColor()
        updateScoreText("SCORE : 0")
        lightOnOffBtn(sequenceGame)
        startCheckingSequence(sequenceGame, sequenceClient, btns)
    }

    private fun getBindingInstance(view: View): Any {
        val method = bindingClass.getDeclaredMethod("bind", View::class.java)
        return method.invoke(null, view)
    }
    private fun updateScoreText(text: String) {
        textView.text = text
    }

    private fun setMap(): Map<Button, Int> {
        val colorList = listOf(R.drawable.btn_blue,R.drawable.btn_green,R.drawable.btn_orange,R.drawable.btn_yellow,R.drawable.btn_cyan,R.drawable.btn_pink,R.drawable.btn_purple,R.drawable.btn_red,R.drawable.btn_purple,R.drawable.btn_orange,R.drawable.btn_blue,R.drawable.btn_cyan,R.drawable.btn_pink,R.drawable.btn_red,R.drawable.btn_yellow,R.drawable.btn_green)
        val map = btns.zip(colorList).toMap()
        return map
    }

    private fun setColor(){
        val map = setMap()
        map.forEach { (button, colorResId) ->
            button.setBackgroundResource(colorResId)
        }
    }

    private fun lightOnBtn(button: Button) {
        button.setBackgroundResource(R.drawable.btn_to_click)
    }

    private fun lightOffBtn(button: Button) {
        val map = setMap()
        val colorResId = map[button]
        if (colorResId != null) {
            button.setBackgroundResource(colorResId)
        }
    }
    private fun makeButtonsUnclickable() {
        for (button in btns) {
            button.isEnabled = false
        }
    }

    private fun makeButtonsClickable() {
        for (button in btns) {
            button.isEnabled = true
        }
    }

    private fun lightOnOffBtn(buttonList: MutableList<Button>) {
        lifecycleScope.launch {
            makeButtonsUnclickable()
            delay(1000)
            for (button in buttonList) {
                lightOnBtn(button)
                delay(1000) // Attendre 1 seconde
                lightOffBtn(button)
                delay(200)

            }
            makeButtonsClickable()
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

    private fun getRandomBtn(btns: List<Button>): Button {
        val randomIndex = Random.nextInt(btns.size)
        return btns[randomIndex]
    }

    private fun addRandomBtnTosequence(buttonList: MutableList<Button>, buttons: List<Button>) {
        var newButton: Button
        do {
            newButton = getRandomBtn(buttons)
        } while (newButton == buttonList.lastOrNull())

        buttonList.add(newButton)

        val sequenceText = buttonList.joinToString(", ") { it.id.toString() }
        Log.d("SequenceClient", "${newButton.id} ajouté à la séquence : $sequenceText")
    }

    private fun startCheckingSequence(sequenceGame: MutableList<Button>, sequenceClient: MutableList<Button>, buttons: List<Button>) {
        lifecycleScope.launch {
            while (true) {
                delay(1000) // Vérifier la séquence chaque seconde (ajustez selon vos besoins)

                val result = checkSequence(sequenceGame, sequenceClient, buttons)
                if (!result) {
                    fragment.findNavController().navigate(R.id.resultFragment)
                    break
                }
            }
        }
    }

    private fun checkSequence(sequenceGame: MutableList<Button>, sequenceClient: MutableList<Button>, buttons: List<Button>): Boolean {
        val minSize = minOf(sequenceGame.size, sequenceClient.size)

        for (i in 0 until minSize) {
            val buttonGame = sequenceGame[i]
            val buttonClient = sequenceClient[i]

            if (buttonGame != buttonClient) {
                // Réinitialiser les séquences
                return false
            }
        }

        if (sequenceGame.size == sequenceClient.size) {
            // La séquence client correspond entièrement à la séquence du jeu
            score = incrementScore(score)
            updateScoreText("SCORE : ${score.toString()}")

            // Réinitialiser la séquence client
            clearSequence(sequenceClient)
            // Ajouter un nouveau bouton à la séquence du jeu
            addRandomBtnTosequence(sequenceGame, buttons)
            // Continuer le jeu
            lightOnOffBtn(sequenceGame)
        }
        return true
    }

    private fun incrementScore(score: Int): Int {
        return score + 1
    }
}
