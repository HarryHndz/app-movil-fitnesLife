package com.harry.fitneslife.fragments

import android.database.Cursor
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.harry.fitneslife.R
import com.harry.fitneslife.adapter.EjerPerAdapter
import com.harry.fitneslife.baseDeDatos.SQLite
import com.harry.fitneslife.baseDeDatos.UserViewFitnexLife
import com.harry.fitneslife.databinding.FragmentRutinaPersonalizadaBinding
import com.harry.fitneslife.databinding.FragmentVistaPersonalizadaBinding
import com.harry.fitneslife.funAndClass.Personalizadas

class VistaPersonalizadaFragment : Fragment() {

    private lateinit var listRutinas: ArrayList<String>
    private val args: VistaPersonalizadaFragmentArgs by navArgs()

    private var _binding: FragmentVistaPersonalizadaBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentVistaPersonalizadaBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initComponent()
    }

    private fun initComponent() {
        val itemId = args.itemId
        //binding.pruebaDeId.text = itemId.toString()

        binding.rvEjerciciosPerso.layoutManager= GridLayoutManager(
            requireContext(),2)

        val registro = getEjercicios()

        if (registro != null && registro.moveToFirst()) {
            do {
                val nombre = registro.getString(3)
                listRutinas.add(nombre!!)
            } while (registro.moveToNext())

        } else {

        }
    }

    private fun getEjercicios(): Cursor? {
        val itemId = args.itemId
        val con = SQLite(requireContext(), "fitlife", null, 4)
        val dataBase = con.writableDatabase
        val consulta = dataBase.rawQuery(
            "select * from ejercicios where id_rutina = '$itemId'",
            null
        )

        return consulta

        dataBase.close()
    }
}