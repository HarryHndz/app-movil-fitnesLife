package com.harry.fitneslife.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.cardview.widget.CardView
import com.harry.fitneslife.R
import com.harry.fitneslife.databinding.ActivityRutinasBinding

class RutinasActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRutinasBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRutinasBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ejerPrin.setOnClickListener {
            principiante()
        }

    }

    private fun principiante(){
        val i = Intent(this, PrincipianteActivity::class.java)
        startActivity(i)
    }
}