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
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.harry.fitneslife.R
import com.harry.fitneslife.adapter.EjerPerAdapter
import com.harry.fitneslife.adapter.ExerciseAdapter
import com.harry.fitneslife.baseDeDatos.SQLite
import com.harry.fitneslife.baseDeDatos.UserViewFitnexLife
import com.harry.fitneslife.baseDeDatos.UserViewFitnexLife.Companion.userData
import com.harry.fitneslife.databinding.FragmentRutinaPersonalizadaBinding

class RutinaPersonalizadaFragment : Fragment() {

    private var _bidind: FragmentRutinaPersonalizadaBinding? =null
    private lateinit var listRutinas : MutableList<String>
    private val binding get() = _bidind!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _bidind = FragmentRutinaPersonalizadaBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listRutinas = mutableListOf<String>() as ArrayList<String>
        initComponets()
        iniListener()
    }

    private fun iniListener() {
        binding.fabAddTask.setOnClickListener { showDialog() }
    }

    private fun showDialog() {
        val dialog = Dialog(requireContext())
        dialog.setContentView(R.layout.dialog_ejercicio)

        val btnAddTask: Button = dialog.findViewById(R.id.btnAddTask)
        val etTask: EditText = dialog.findViewById(R.id.etTask)

        btnAddTask.setOnClickListener {
            val nombre = etTask.text.toString()
            if (nombre.isNotEmpty()) {
                crearRutina(nombre)
                dialog.hide()
            }
        }

        dialog.show()
    }

    private fun crearRutina(nombre: String) {
        val id = userData.getId()
        var con= SQLite(requireContext(), "fitlife", null, 3)
        var dataBase = con.writableDatabase
        var registro = ContentValues()

        registro.put("user_id", id)
        registro.put("nombre",nombre)

        dataBase.insert("rutinasPer",null,registro)

        dataBase.close()
    }

    private fun initComponets() {

        binding.rvEjerciciosPerso.layoutManager= GridLayoutManager(
            requireContext(),2)

        val registro = rutinasPerso()

        if (registro != null && registro.moveToFirst()) {
            do {
                val nombre = registro.getString(0)
                listRutinas.add(nombre!!)
            } while (registro.moveToNext())

            binding.rvEjerciciosPerso.adapter = EjerPerAdapter(listRutinas)

            
        } else {

        }
    }

    private fun rutinasPerso(): Cursor? {
        val id = userData.getId()
        Log.i("ciclo", id.toString())
        val con = SQLite(requireContext(), "fitlife", null, 3)
        val dataBase = con.writableDatabase
        val consulta = dataBase.rawQuery(
            "select nombre from rutinasPer where user_id = '$id'",
            null
        )

        return consulta
        dataBase.close()
    }

    override fun onResume() {
        super.onResume()
        initComponets()
    }

}