package com.example.simon.activities

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.simon.R
import com.google.firebase.auth.FirebaseAuth

class Register : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)


        val loginBtn = findViewById<TextView>(R.id.loginNow)
        loginBtn.setOnClickListener {
            val intent = Intent(this, Login::class.java)
            intent.putExtra("key", "value")
            startActivity(intent)
            finish()
        }


        val editTextMail = findViewById<TextView>(R.id.email)
        val editTextPassword = findViewById<TextView>(R.id.password)
        val buttonReg = findViewById<Button>(R.id.btnRegister)
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)

        buttonReg.setOnClickListener {
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
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    progressBar.visibility = View.GONE

                    if (task.isSuccessful) {
                        Log.d(TAG, "createUserWithEmail:success")
                        Toast.makeText(this, "Account created.", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, Login::class.java)
                        intent.putExtra("key", "value")
                        startActivity(intent)
                        finish()
                    } else {
                        Log.w(TAG, "createUserWithEmail:failure", task.exception)
                        var probleme = ""
                        if(password.length < 6) {probleme = "Password must be at least 6 entries long"}
                        Toast.makeText(this, "Registering failed."+probleme, Toast.LENGTH_SHORT).show()
                    }
                }

        }

    }
}
