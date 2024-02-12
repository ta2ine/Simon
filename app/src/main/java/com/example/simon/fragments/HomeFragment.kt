package com.example.simon.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.simon.R
import com.example.simon.activities.Login
import com.example.simon.AdapterClassement
import com.example.simon.data.getTop10UsersFromFirebase
import com.example.simon.databinding.FragmentHomeBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null // Data binding
    private val binding get() = _binding!!
    private var level: String = "Medium"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val auth = FirebaseAuth.getInstance()


        val currentUser = auth.currentUser
        val logOutBtn = binding.logOutBtn
        val userDetails = binding.userDetails

        val easyBtn = binding.easyBtn
        val mediumBtn = binding.mediumBtn
        val hardBtn = binding.hardBtn

        if (currentUser == null){
                val intent = Intent(requireActivity(), Login::class.java)
                intent.putExtra("key", "value")
                startActivity(intent)
        }else{
            userDetails.text = currentUser.email
        }

        logOutBtn.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(requireActivity(), Login::class.java)
            intent.putExtra("key", "value")
            startActivity(intent)
        }

        // Ajouter un listener au bouton radio Easy
        easyBtn.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                level = "Easy"
                refreshRecyclerView()
            }
        }

        // Ajouter un listener au bouton radio Medium
        mediumBtn.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                level = "Medium"
                refreshRecyclerView()
            }
        }

        // Ajouter un listener au bouton radio Hard
        hardBtn.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                level = "Hard"
                refreshRecyclerView()
            }
        }

        mediumBtn.isChecked = true
        val playButton = binding.playBtn
        playButton.setOnClickListener {
            val checkedId = binding.radioGroup.checkedRadioButtonId
            if (checkedId != -1) {
                val checkedAnswer = binding.radioGroup.indexOfChild(view.findViewById(checkedId))
                if (checkedAnswer == 0) {
                    level="Easy"
                    findNavController().navigate(R.id.action_homeFragment2_to_EasyMode)
                } else if (checkedAnswer == 1){
                    level="Medium"
                    findNavController().navigate(R.id.action_homeFragment2_to_mediumModeFragment2)
                }
                else if (checkedAnswer == 2){
                    level="Hard"
                    findNavController().navigate(R.id.action_homeFragment2_to_hardModeFragment)
                }

            }
        }
        refreshRecyclerView()
    }

    fun refreshRecyclerView(){
        //récupération des scores
        getTop10UsersFromFirebase(level, requireContext()) { topUsers -> //défintion du recyclerView pour le classement
            val recyclerView = binding?.RecyclerView
            recyclerView?.layoutManager = LinearLayoutManager(requireContext()) //requireContext car on est dans un fragment ici
            recyclerView?.adapter = AdapterClassement(topUsers)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Nettoyer le binding
    }
}