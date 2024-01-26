package com.harry.fitneslife.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.harry.fitneslife.R
import com.harry.fitneslife.databinding.ActivityInicioBinding

class InicioActivity : AppCompatActivity() {
    private lateinit var binding : ActivityInicioBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInicioBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.textResgis.setOnClickListener {
            goToRegister() }

        binding.BtnSend.setOnClickListener {
            goToHome() }
    }

    private fun goToRegister(){
        val x = Intent(this, RegistroActivity::class.java)
        startActivity(x)
    }

    private fun goToHome(){
        val x = Intent(this, HomeActivity::class.java)
        startActivity(x)
    }
}