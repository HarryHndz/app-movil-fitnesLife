package com.harry.fitneslife.activities

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.harry.fitneslife.R
import com.harry.fitneslife.baseDeDatos.SQLite
import com.harry.fitneslife.databinding.ActivityRegistroBinding

class RegistroActivity : AppCompatActivity() {

    private lateinit var binding : ActivityRegistroBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegistroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.textIni.setOnClickListener { goToLogIn() }

        binding.BtnSend.setOnClickListener { validarCampos() }

    }

    private fun validarCampos() {
        var nombre = binding.EditName?.text.toString()
        var email = binding.EditEmail?.text.toString()
        var pass = binding.EditPassword?.text.toString()
        var passConfirm = binding.EditRptPass?.text.toString()

        if (nombre.isNotEmpty() && email.isNotEmpty() && pass.isNotEmpty()) {
            if(longitudContraseña(pass)) {
                if (validarContraseña(pass, passConfirm)) {
                    if(buscarRegistro(email)) {
                        registrar(nombre, email, pass)
                    } else {
                        Toast.makeText(this,"Ya hay una cuenta con ese correo", Toast.LENGTH_LONG).show()
                    }
                } else {
                    Toast.makeText(this,"Las contraseñas no coinciden", Toast.LENGTH_LONG).show()
                }
            } else {
                Toast.makeText(this,"La contraseña debe tener por lo menos 6 caracteres", Toast.LENGTH_LONG).show()
            }
        } else {
            Toast.makeText(this,"Complete todos los campos vacios", Toast.LENGTH_LONG).show()
        }

    }

    private fun registrar(nombre: String, email: String, pass: String) {
        var con= SQLite(this, "fitlife", null, 1)
        var dataBase = con.writableDatabase
        var registro = ContentValues()

        registro.put("nombre",nombre)
        registro.put("correo",email)
        registro.put("contraseña",pass)
        dataBase.insert("usuarios",null,registro)

        Toast.makeText(this,"Su reguistro exitoso", Toast.LENGTH_LONG).show()
        Log.i("Hola", "Registro exitoso")
        dataBase.close()
        goToLogIn()
    }

    private fun buscarRegistro(email:String):Boolean {
        val con=SQLite(this, "fitlife", null, 1)
        val baseDatos=con.writableDatabase
        val fila = baseDatos.rawQuery("select nombre, correo from usuarios where correo = '$email'", null)
        if (fila != null && fila.moveToFirst()) {
            baseDatos.close()
            return false
        } else {
            baseDatos.close()
            return true
        }
    }

    private fun longitudContraseña(pass: String): Boolean {
        return pass.length >= 6
    }
    private fun validarContraseña(pass: String, passConfir: String): Boolean {
        return pass == passConfir
    }

    private fun goToLogIn(){
        val x = Intent(this, InicioActivity::class.java)
        startActivity(x)
    }
}