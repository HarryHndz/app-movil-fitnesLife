package com.harry.fitneslife.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.harry.fitneslife.adapter.HomeAdaptaer
import com.harry.fitneslife.baseDeDatos.UserViewFitnexLife.Companion.userData
import com.harry.fitneslife.databinding.ActivityHomeBinding
import com.harry.fitneslife.funAndClass.ListBox

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.i("ciclo", "Create")
        initComponent()
        iniciarSesion()
        binding.btnMoreExercise.setOnClickListener {
            goToPrin()
        }

    }

    override fun onStart() {
        super.onStart()
        Log.i("ciclo", "Start")
    }

    override fun onResume() {
        super.onResume()

        Log.i("ciclo", "Resume")
    }

    override fun onPause() {
        super.onPause()
        Log.i("ciclo", "Pause")
    }

    override fun onStop() {
        super.onStop()
        Log.i("ciclo", "Stop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("ciclo", "Destroy")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i("ciclo", "Restart")
    }

    private fun iniciarSesion() {
        binding.back.setOnClickListener {
            userData.wipe()
            startActivity(Intent(this, InicioActivity::class.java))
        }
        val name = userData.getName()
        binding.tvSaludo.text = name
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