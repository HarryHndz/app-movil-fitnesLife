package com.harry.fitneslife.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.harry.fitneslife.R
import com.harry.fitneslife.baseDeDatos.SQLite
import com.harry.fitneslife.baseDeDatos.UserViewFitnexLife.Companion.userData
import com.harry.fitneslife.databinding.ActivityInicioBinding

class InicioActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInicioBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInicioBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.i("ciclo", "onCreate")
        checkUserValues()

    }

    override fun onStart() {
        super.onStart()
        Log.i("ciclo", "onStart")
    }

    override fun onResume() {
        super.onResume()
        binding.textResgis.setOnClickListener { goToRegister() }
        binding.BtnSend.setOnClickListener { validarCampos() }

        Log.i("ciclo", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.i("ciclo", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.i("ciclo", "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("ciclo", "onDestroy")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i("ciclo", "onRestart")
        if (userData.getName().isNotEmpty()) {
            goToHome()
        }
    }

    private fun validarCampos() {
        var email = binding.editEmail?.text.toString()
        var pass = binding.EditPassword?.text.toString()

        if (email.isNotEmpty() && pass.isNotEmpty()) {
            confirmarUsuario(email, pass)
        } else {
            Toast.makeText(this, "Debe llenar todos los campos", Toast.LENGTH_SHORT).show()
        }
    }

    private fun confirmarUsuario(email: String, pass: String) {
        val con = SQLite(this, "fitlife", null, 1)
        val baseDatos = con.writableDatabase
        val fila = baseDatos.rawQuery(
            "select nombre, correo, contraseña from usuarios where correo = '$email'",
            null
        )
        if (fila != null && fila.moveToFirst()) {
            if (pass == fila.getString(2)) {
                val nombre = fila.getString(0)
                val correo = fila.getString(1)
                iniciarSesion(nombre, correo)
            } else {
                Toast.makeText(this, "Contraseña Incorrecta", Toast.LENGTH_SHORT).show()
            }
            baseDatos.close()
        } else {
            Toast.makeText(this, "No hay registro con ese correo", Toast.LENGTH_SHORT).show()
            baseDatos.close()
        }

    }

    fun checkUserValues() {
        if (userData.getName().isNotEmpty()) {
            goToHome()
        }
    }

    private fun iniciarSesion(nombre: String, correo: String) {
        userData.saveNombre(nombre)
        userData.saveEmail(correo)
        goToHome()
    }

    private fun goToRegister() {
        val x = Intent(this, RegistroActivity::class.java)
        startActivity(x)
    }

    private fun goToHome() {
        val x = Intent(this, MenuActivity::class.java)
        startActivity(x)
    }
}