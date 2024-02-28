package com.harry.fitneslife.activities

import android.app.Dialog
import android.content.ContentValues
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.harry.fitneslife.R
import com.harry.fitneslife.baseDeDatos.SQLite
import com.harry.fitneslife.baseDeDatos.UserViewFitnexLife
import com.harry.fitneslife.databinding.ActivityRegistroBinding

class RegistroActivity : AppCompatActivity() {

    private lateinit var binding : ActivityRegistroBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("ciclo", "onCreateRegistro")

        binding = ActivityRegistroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.textIni.setOnClickListener { goToLogIn() }

        binding.BtnSend.setOnClickListener { validarCampos() }

    }

    override fun onStart() {
        super.onStart()
        Log.i("ciclo", "onStartRegistro")
    }

    override fun onResume() {
        super.onResume()
        Log.i("ciclo", "onResumeRegistro")
    }

    override fun onPause() {
        super.onPause()
        Log.i("ciclo", "onPauseRegistro")
    }

    override fun onStop() {
        super.onStop()
        Log.i("ciclo", "onStopRegistro")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("ciclo", "onDestroyRegistro")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i("ciclo", "onRestartRegistro")
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
                        showDialog("Ya hay una cuenta con ese correo")
                        Toast.makeText(this,"Ya hay una cuenta con ese correo", Toast.LENGTH_LONG).show()
                    }
                } else {
                    showDialog("Las contraseñas no coinciden")
                    Toast.makeText(this,"Las contraseñas no coinciden", Toast.LENGTH_LONG).show()
                }
            } else {
                showDialog("La contraseña debe tener por lo menos 6 caracteres")
                Toast.makeText(this,"La contraseña debe tener por lo menos 6 caracteres", Toast.LENGTH_LONG).show()
            }
        } else {
            showDialog("Complete todos los campos vacios")
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
        finish()
    }

    private fun showDialog(alert: String) {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_alert)

        dialog.window?.setBackgroundDrawableResource(R.drawable.dialog_border)

        val btn: Button = dialog.findViewById(R.id.btnConfirmacion)
        val tvWarning: TextView = dialog.findViewById(R.id.tvWarning)
        tvWarning.text = alert

        btn.setOnClickListener { dialog.hide() }

        dialog.show()
    }

}