package com.harry.fitneslife.fragments

import android.app.Dialog
import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Button
import android.widget.EditText
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.harry.fitneslife.R
import com.harry.fitneslife.adapter.EjerPerAdapter
import com.harry.fitneslife.baseDeDatos.SQLite
import com.harry.fitneslife.baseDeDatos.UserViewFitnexLife.Companion.userData
import com.harry.fitneslife.databinding.FragmentRutinaPersonalizadaBinding
import com.harry.fitneslife.funAndClass.OnItemClickListener
import com.harry.fitneslife.funAndClass.Personalizadas
import kotlin.collections.mapIndexed

class RutinaPersonalizadaFragment : Fragment(), OnItemClickListener {

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
                initComponets()
            }
        }

        dialog.show()
    }

    private fun crearRutina(nombre: String) {
        val id = userData.getId()
        var con= SQLite(requireContext(), "fitlife", null, 4)
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

        // Aquí deberías obtener los datos de tu base de datos SQLite
        val dataListFromSQLite = rutinasPerso()

        val dataListWithIDs = dataListFromSQLite.mapIndexed { _, data ->
            Personalizadas(data.id_rutina, data.user_id, data.nombre) // El ID podría ser el índice + 1
        }

        binding.rvEjerciciosPerso.adapter = EjerPerAdapter(dataListWithIDs, this)

        /*val registro = rutinasPerso()

        if (registro != null && registro.moveToFirst()) {
            do {
                val nombre = registro.getString(0)
                listRutinas.add(nombre!!)
            } while (registro.moveToNext())

            binding.rvEjerciciosPerso.adapter = EjerPerAdapter(listRutinas)

            
        } else {

        }*/
    }

    private fun rutinasPerso(): List<Personalizadas> {
        val dataList = mutableListOf<Personalizadas>()
        val id = userData.getId()
        Log.i("ciclo", id.toString())
        val con = SQLite(requireContext(), "fitlife", null, 4)
        val dataBase = con.writableDatabase
        val consulta = dataBase.rawQuery(
            "select * from rutinasPer where user_id = '$id'",
            null
        )

        consulta.use { cursor ->
            while (cursor.moveToNext()) {
                val id = cursor.getInt(0)
                val idUsuario = cursor.getInt(1)
                val nombre = cursor.getString(2)

                val tuModelo = Personalizadas(id, idUsuario, nombre)
                dataList.add(tuModelo)
            }
        }
        return dataList
        /*return consulta*/

        dataBase.close()
    }

    override fun onResume() {
        super.onResume()
        initComponets()
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        initComponets()
    }

    override fun onItemClick(id: Int) {
        val action = RutinaPersonalizadaFragmentDirections.actionRutinaPersonalizadaFragmentToVistaPersonalizadaFragment(itemId = id)
        findNavController().navigate(action)
    }

}