package com.example.simon.fragments

import android.content.ContentValues.TAG
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.simon.R
import com.example.simon.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth


class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val editTextMail = binding.email
        val editTextPassword = binding.password
        val buttonLogin = binding.btnLogin
        val progressBar = binding.progressBar
        val registerBtn = binding.registerNow

        registerBtn.setOnClickListener {
            findNavController().navigate(R.id.loginFragment)
        }

        buttonLogin.setOnClickListener{
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

            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(requireActivity()) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "signInWithEmail:success")
                        val user = auth.currentUser
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "signInWithEmail:failure", task.exception)
                        Toast.makeText(requireContext(),"Authentication failed.",Toast.LENGTH_SHORT,).show()
                    }
                }

        }
    }
}