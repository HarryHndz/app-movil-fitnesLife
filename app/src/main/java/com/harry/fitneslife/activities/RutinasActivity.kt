package com.harry.fitneslife.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.cardview.widget.CardView
import com.harry.fitneslife.R

class RutinasActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rutinas)

        val prin = findViewById<CardView>(R.id.ejerPrin)

        prin.setOnClickListener {
            principiante()
        }

    }

    private fun principiante(){
        val i = Intent(this, PrincipianteActivity::class.java)
        startActivity(i)
    }

}