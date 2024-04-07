package com.harry.fitneslife.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.harry.fitneslife.EjerciciosData.ExerciseResponse
import com.harry.fitneslife.R
import com.harry.fitneslife.databinding.FragmentDetailExerBinding
import com.squareup.picasso.Picasso

class DetailExerFragment : Fragment() {
    private var _binding: FragmentDetailExerBinding? = null
    private lateinit var dbrfe : DatabaseReference
    private val binding  get() = _binding!!

    val args:DetailExerFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        _binding = FragmentDetailExerBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val nombre : String? = args.nombreEjer
        if (nombre != null) {
            getEjercicio(nombre)
        }

    }

    private fun getEjercicio(nombre: String){
        dbrfe = FirebaseDatabase.getInstance().getReference("Ejercicio")
        val query = dbrfe.orderByChild("nombre").equalTo(nombre)
        query.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                for (data in snapshot.children){
                    val ejercicioData = data.getValue(ExerciseResponse::class.java)
                    if (ejercicioData != null) {
                        binding.nombreEjer.text = ejercicioData.nombre
                        binding.detallesEjercicio.text = ejercicioData.detalles
                        binding.ejecucionEjercicio.text = ejercicioData.ejecucion
                        binding.preparacionEjercicio.text= ejercicioData.preparacion
                        Picasso.get().load(ejercicioData.imagen).into(binding.imagenEjercicio)
                    }
                }

            }

            override fun onCancelled(error: DatabaseError) {
                Log.w("firebase", "Failed to read value.", error.toException())
            }

        })
    }
}