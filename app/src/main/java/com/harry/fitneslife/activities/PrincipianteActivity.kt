package com.harry.fitneslife.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.harry.fitneslife.R
import com.harry.fitneslife.databinding.ActivityPrincipianteBinding

class PrincipianteActivity : AppCompatActivity() {

    private lateinit var binding : ActivityPrincipianteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPrincipianteBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}