package com.harry.fitneslife.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.harry.fitneslife.R
import com.harry.fitneslife.adapter.HomeAdaptaer
import com.harry.fitneslife.databinding.ActivityHomeBinding
import com.harry.fitneslife.funAndClass.HomeBox
import com.harry.fitneslife.funAndClass.ListBox

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initComponent()
        binding.homeTxt.setOnClickListener {
            goToPrin()
        }

    }

    private fun goToPrin() {
        val i = Intent(this, RutinasActivity::class.java)
        startActivity(i)
    }

    private fun initComponent() {
        binding.recicleHome.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.recicleHome.adapter = HomeAdaptaer(ListBox.carrusel)
    }
}