package com.harry.fitneslife.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.harry.fitneslife.R

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val exercise = findViewById<TextView>(R.id.home_tt)

        exercise.setOnClickListener {
            goToPrin()
        }
    }

    private fun goToPrin() {
        val i = Intent(this, RutinasActivity::class.java)
        startActivity(i)
    }
}