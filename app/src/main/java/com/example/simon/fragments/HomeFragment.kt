package com.example.simon.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.simon.R
import com.example.simon.activities.Login
import com.example.simon.activities.MainActivity
import com.example.simon.databinding.FragmentHomeBinding
import com.google.firebase.auth.FirebaseAuth


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

        easyBtn.isChecked = true
        val playButton = binding.playBtn
        playButton.setOnClickListener {
            val checkedId = binding.radioGroup.checkedRadioButtonId
            if (checkedId != -1) {
                val checkedAnswer = binding.radioGroup.indexOfChild(view.findViewById(checkedId))
                if (checkedAnswer == 0) {
                    findNavController().navigate(R.id.action_homeFragment2_to_EasyMode)
                } else if (checkedAnswer == 1){
                    findNavController().navigate(R.id.action_homeFragment2_to_mediumModeFragment2)
                }
                else if (checkedAnswer == 2){
                    findNavController().navigate(R.id.action_homeFragment2_to_hardModeFragment)
                }
            }
        }

    }
}