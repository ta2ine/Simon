package com.example.simon.fragments

import android.content.Intent
import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.simon.R
import com.example.simon.data.DataScore
import com.example.simon.data.envoieScoreEnLigne
import com.example.simon.data.Score
import com.example.simon.databinding.FragmentResultBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

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

        Log.w(ContentValues.TAG, "Vérification email :"+FirebaseAuth.getInstance().currentUser?.email)
        val userId = FirebaseAuth.getInstance().currentUser?.uid //récupération de 'id courant
        val level = arguments?.getString("level", "Easy") // Le niveau de difficulté renvoyé par le fragment précédent
        Log.w(ContentValues.TAG, "Emplacement pour firebase : "+level+" score saisi : "+score)


        val databaseReference = FirebaseDatabase.getInstance().getReference("scores/$level")


        val dataScore = DataScore(userId, score, FirebaseAuth.getInstance().currentUser?.email)
        envoieScoreEnLigne(databaseReference, dataScore, requireContext())

        val scoreBtn = view.findViewById<Button>(R.id.scoreBtn)
        scoreBtn.setOnClickListener {
            val intent = Intent()
            intent.action = Intent.ACTION_SEND
            intent.putExtra(Intent.EXTRA_TEXT, score)
            intent.type="text/plain"

            startActivity(Intent.createChooser(intent,"Envoyer à :"))


        }

    }

}