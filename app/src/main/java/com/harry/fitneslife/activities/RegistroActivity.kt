package com.harry.fitneslife.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.harry.fitneslife.R

class RegistroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        val btnInicio = findViewById<TextView>(R.id.textIni)
        val name = findViewById<EditText>(R.id.EditName)
        val email = findViewById<EditText>(R.id.EditEmail)
        val pass = findViewById<EditText>(R.id.EditPassword)
        val passVerify = findViewById<EditText>(R.id.EditRptPass)

        btnInicio.setOnClickListener {
            goToLogIn()
        }
    }

    private fun goToLogIn(){
        val x = Intent(this, InicioActivity::class.java)
        startActivity(x)
    }
}