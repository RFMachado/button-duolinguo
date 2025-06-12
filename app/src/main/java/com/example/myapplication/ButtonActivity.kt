package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityButtonBinding

class ButtonActivity : AppCompatActivity() {
    private lateinit var binding: ActivityButtonBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityButtonBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}