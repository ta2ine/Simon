package com.example.simon.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.simon.R
import com.example.simon.databinding.FragmentHomeBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: FragmentHomeBinding // Data binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }
}