package com.harry.fitneslife.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.harry.fitneslife.EjerciciosData.ExerciseResponse
import com.harry.fitneslife.R
import com.harry.fitneslife.adapter.rutinaPrincipiante.PrincipianteAdapter
import com.harry.fitneslife.databinding.FragmentRoutinePrincipianteBinding
import kotlin.math.log


class RoutinePrincipianteFragment : Fragment() {

    private var _binding : FragmentRoutinePrincipianteBinding? = null
    private  val binding get() = _binding!!
    private lateinit var dbrefRutinas : DatabaseReference
    private lateinit var listRutinaEjer : ArrayList<ExerciseResponse>
    private lateinit var ejercicioAdapter: PrincipianteAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentRoutinePrincipianteBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listRutinaEjer = arrayListOf()
        init()
    }

    private fun init() {
        ejercicioAdapter = PrincipianteAdapter(listRutinaEjer)
        binding.listRutinaEjer.layoutManager = LinearLayoutManager(
            requireContext(),LinearLayoutManager.VERTICAL,false
        )
        binding.listRutinaEjer.adapter =ejercicioAdapter
        getRutinaEjercicio()

        binding.btnEmpezarRut.setOnClickListener {
            findNavController().navigate(R.id.action_routinePrincipiante_to_cronometroRutinaFragment)
        }
    }

    private fun getRutinaEjercicio() {
        dbrefRutinas = FirebaseDatabase.getInstance().reference.child("RutinasEjercicios").child("principiante")
        dbrefRutinas.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (snapshot in dataSnapshot.children) {
                    val ejercicioKey = snapshot.key
                    val dbrefEjercicio = ejercicioKey?.let {
                        FirebaseDatabase.getInstance().reference.child("Ejercicio").child(
                            it
                        )
                    }
                    dbrefEjercicio?.addListenerForSingleValueEvent(object : ValueEventListener {
                        override fun onDataChange(ejercicioSnapshot: DataSnapshot) {
                            val ejerData = ejercicioSnapshot.getValue(ExerciseResponse::class.java)
                            if (ejerData != null) {
                                listRutinaEjer.add(ejerData)
                                ejercicioAdapter.notifyDataSetChanged()
                            }
                        }
                        override fun onCancelled(databaseError: DatabaseError) {
                        }
                    })
                }
            }
            override fun onCancelled(databaseError: DatabaseError) {
            }
        })

    }



}