package com.harry.fitneslife.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.harry.fitneslife.R

class InicioActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio)

        val btnRegister = findViewById<TextView>(R.id.textResgis)
        val btnSend = findViewById<Button>(R.id.BtnSend)

        btnRegister.setOnClickListener {
            goToRegister()
        }

        btnSend.setOnClickListener {
            goToHome()
        }

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