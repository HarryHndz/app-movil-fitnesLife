package com.harry.fitneslife.fragments

import android.app.Dialog
import android.content.ContentValues
import android.database.Cursor
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.harry.fitneslife.R
import com.harry.fitneslife.baseDeDatos.SQLite
import com.harry.fitneslife.baseDeDatos.UserViewFitnexLife.Companion.userData
import com.harry.fitneslife.databinding.FragmentCountBinding


class CountFragment : Fragment() {

    private var _binding: FragmentCountBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCountBinding.inflate(inflater, container, false)
        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initComponents()
    }

    private fun initComponents() {
        var datos = buscarDatos()
        if (datos != null) {
            binding.etName.setText(datos.getString(0).toString())
            binding.etEmail.setText(datos.getString(1))
            binding.tvImc.text = datos.getString(2)
        }

        binding.btnActualizar.setOnClickListener {
            val act = actualizarCampos()
            if (act) {
                /*userData.wipe()
                val correo = binding.etEmail.toString()
                val fila = datos(correo)
                if (fila != null) {
                    reestablecer(fila)
                }*/
                showDialog("Se actualizaron los datos")
            }
            /*actualizarCampos()*/
        }

        binding.btnCancelar.setOnClickListener { initComponents() }
    }

    private fun datos(email: String): Cursor? {
        val con = SQLite(requireContext(), "fitlife", null, 3)
        val baseDatos = con.writableDatabase
        val fila = baseDatos.rawQuery(
            "select nombre, correo, imc from usuarios where correo = '$email'",
            null
        )
        fila.moveToFirst()
        return fila
        baseDatos.close()
    }

    private fun buscarDatos(): Cursor? {
        val correo = userData.getEmail()
        Log.i("ciclo", correo)
        val con = SQLite(requireContext(), "fitlife", null, 3)
        val dataBase = con.writableDatabase
        val consulta = dataBase.rawQuery(
            "select nombre, correo, imc from usuarios where correo = '$correo'",
            null
        )
        consulta.moveToFirst()
        return consulta
        dataBase.close()
    }

    private fun actualizarCampos(): Boolean {
        val changeName = binding.etName?.text.toString()
        val changeCorreo = binding.etEmail?.text.toString()

        var correo = userData.getEmail()
        var con = SQLite(requireContext(), "fitlife", null, 3)
        var dataBase = con.writableDatabase
        val values = ContentValues()
        values.put("nombre", changeName)
        values.put("correo", changeCorreo)
        dataBase.update("usuarios", values, "correo=?", arrayOf(correo))
        dataBase.close()
        return true
    }

    private fun showDialog(alert: String) {
        val dialog = Dialog(requireContext())
        dialog.setContentView(R.layout.dialog_alert_succesful)
        dialog.window?.setBackgroundDrawableResource(R.drawable.dialog_border)

        val btn: Button = dialog.findViewById(R.id.btnConfirmacion)
        val tvWarning: TextView = dialog.findViewById(R.id.tvWarning)
        tvWarning.text = alert

        btn.setOnClickListener { dialog.hide() }

        dialog.show()
    }

    private fun reestablecer(fila: Cursor) {
        userData.saveNombre(fila.getString(0))
        userData.saveEmail(fila.getString(1))
        userData.saveImc(fila.getString(2))
        initComponents()
    }
}