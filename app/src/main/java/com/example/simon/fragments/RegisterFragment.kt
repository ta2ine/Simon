package com.example.simon.fragments

import android.content.ContentValues.TAG
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.example.simon.R
import com.example.simon.databinding.FragmentRegisterBinding
import com.google.firebase.auth.FirebaseAuth


class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val editTextMail = binding.email
        val editTextPassword = binding.password
        val buttonReg = binding.btnRegister
        val progressBar = binding.progressBar
        val loginBtn = binding.loginNow

        loginBtn.setOnClickListener {
                findNavController().navigate(R.id.loginFragment)
        }

        buttonReg.setOnClickListener{
            progressBar.visibility = View.VISIBLE

            val email = editTextMail.text.toString()
            if (TextUtils.isEmpty(email)){
                Toast.makeText(requireContext(),"Enter email", Toast.LENGTH_SHORT).show();
            }

            val password = editTextPassword.text.toString()
            if (TextUtils.isEmpty(password)){
                Toast.makeText(requireContext(),"Enter password", Toast.LENGTH_SHORT).show();
            }

            val auth = FirebaseAuth.getInstance()

            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(requireActivity()) { task ->
                    progressBar.visibility = View.GONE

                    if (task.isSuccessful) {
                        Log.d(TAG, "createUserWithEmail:success")
                        Toast.makeText(requireContext(),"Account created.",Toast.LENGTH_SHORT).show()

                    } else {
                        Log.w(TAG, "createUserWithEmail:failure", task.exception)
                        Toast.makeText(requireContext(),"Authentication failed.",Toast.LENGTH_SHORT).show()
                    }
                }


        }
    }
}