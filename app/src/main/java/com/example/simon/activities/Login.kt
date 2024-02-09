package com.example.simon.activities

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import com.example.simon.R
import com.google.firebase.auth.FirebaseAuth

class Login : AppCompatActivity() {

    public override fun onStart() {
        super.onStart()
        val auth = FirebaseAuth.getInstance()
        val currentUser = auth.currentUser
        if (currentUser != null) {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("key", "value")
            startActivity(intent)        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val registerBtn = findViewById<TextView>(R.id.registerNow)
        registerBtn.setOnClickListener {
            val intent = Intent(this, Register::class.java)
            intent.putExtra("key", "value")
            startActivity(intent)
        }

        val editTextMail = findViewById<TextView>(R.id.email)
        val editTextPassword = findViewById<TextView>(R.id.password)
        val buttonLogin = findViewById<Button>(R.id.btnLogin)
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)

        buttonLogin.setOnClickListener{
            progressBar.visibility = View.VISIBLE

            val email = editTextMail.text.toString()
            if (TextUtils.isEmpty(email)) {
                Toast.makeText(this, "Enter email", Toast.LENGTH_SHORT).show()
            }
            val password = editTextPassword.text.toString()
            if (TextUtils.isEmpty(password)) {
                Toast.makeText(this, "Enter password", Toast.LENGTH_SHORT).show()
            }


            val auth = FirebaseAuth.getInstance()



            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    progressBar.visibility = View.GONE
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        val intent = Intent(this, MainActivity::class.java)
                        intent.putExtra("key", "value")
                        startActivity(intent)
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "signInWithEmail:failure", task.exception)
                        Toast.makeText(this,"Authentication failed.",Toast.LENGTH_SHORT,).show()
                    }
                }

        }
    }
}
