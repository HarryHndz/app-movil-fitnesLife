package com.harry.fitneslife.activities

import android.app.Dialog
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
        Log.i("ciclo", "onCreateInicio")
        checkUserValues()

    }

    override fun onStart() {
        super.onStart()
        Log.i("ciclo", "onStartInicio")
    }

    override fun onResume() {
        super.onResume()
        binding.textResgis.setOnClickListener { goToRegister() }
        binding.BtnSend.setOnClickListener { validarCampos() }

        Log.i("ciclo", "onResumeInicio")
    }

    override fun onPause() {
        super.onPause()
        Log.i("ciclo", "onPauseInicio")
    }

    override fun onStop() {
        super.onStop()
        Log.i("ciclo", "onStopInicio")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("ciclo", "onDestroyInicio")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i("ciclo", "onRestartInicio")
    }

    private fun validarCampos() {
        var email = binding.editEmail?.text.toString()
        var pass = binding.EditPassword?.text.toString()

        if (email.isNotEmpty() && pass.isNotEmpty()) {
            confirmarUsuario(email, pass)
        } else {
            showDialog(getString(R.string.resuelva))
            Toast.makeText(this, getString(R.string.resuelva), Toast.LENGTH_SHORT).show()
        }
    }

    private fun confirmarUsuario(email: String, pass: String) {
        val con = SQLite(this, "fitlife", null, 4)
        val baseDatos = con.writableDatabase
        val fila = baseDatos.rawQuery(
            "select user_id, nombre, correo, contraseña, imc from usuarios where correo = '$email'",
            null
        )
        if (fila != null && fila.moveToFirst()) {
            if (pass == fila.getString(3)) {
                val id = fila.getInt(0)
                val nombre = fila.getString(1)
                val correo = fila.getString(2)
                var imc: String = fila.getString(4)
                iniciarSesion(id ,nombre, correo, imc)
            } else {
                showDialog(getString(R.string.contaseñaIncorrecta))
                Toast.makeText(this, getString(R.string.contaseñaIncorrecta), Toast.LENGTH_SHORT).show()
            }
            baseDatos.close()
        } else {
            showDialog(getString(R.string.notFound))
            Toast.makeText(this, getString(R.string.notFound), Toast.LENGTH_SHORT).show()
            baseDatos.close()
        }

    }

    fun checkUserValues() {
        if (userData.getName().isNotEmpty()) {
            goToHome()
        }
    }

    private fun iniciarSesion(id: Int ,nombre: String, correo: String, imc: String) {
        userData.saveId(id)
        userData.saveNombre(nombre)
        userData.saveEmail(correo)
        userData.saveImc(imc)

        goToHome()
    }

    private fun goToRegister() {
        val x = Intent(this, RegistroActivity::class.java)
        startActivity(x)
        finish()
    }

    private fun goToHome() {
        val x = Intent(this, MenuActivity::class.java)
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