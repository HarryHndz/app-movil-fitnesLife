package com.harry.fitneslife.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigator
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.harry.fitneslife.EjerciciosData.ExerciseResponse
import com.harry.fitneslife.R
import com.harry.fitneslife.adapter.ExerciseAdapter
import com.harry.fitneslife.databinding.FragmentEjercicioBinding


class EjercicioFragment : Fragment() {

    private lateinit var dbref : DatabaseReference
    private lateinit var listEjercicios : ArrayList<ExerciseResponse>
    private var _bindig : FragmentEjercicioBinding? = null
    //private lateinit var myAdapter : ExerciseAdapter
    private val binding get() = _bindig!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _bindig = FragmentEjercicioBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listEjercicios = arrayListOf()
        init()
    }

    private  fun init(){
        binding.recycleEjercicios.layoutManager= GridLayoutManager(
            requireContext(),2)

        getDataEjercicio()

    }

    private fun getDataEjercicio(){
        dbref = FirebaseDatabase.getInstance().getReference("Ejercicio")
        dbref.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                Log.d("firebase","llamada exitosa")
                if (snapshot.exists()){

                    for(EjercicioSnapshot in snapshot.children){
                        val ejer = EjercicioSnapshot.getValue(ExerciseResponse::class.java)
                        listEjercicios.add(ejer!!)

                    }
                    binding.recycleEjercicios.adapter = ExerciseAdapter(listEjercicios)
                }
            }
            override fun onCancelled(error: DatabaseError) {
                Log.w("firebase", "Failed to read value.", error.toException())
            }
        })
    }

//    fun onSelectEjercicio(ejercicio: ExerciseResponse){
//        findNavController().navigate(EjercicioFragmentDirections.actionNavEjerciciosToDetailExerFragment(ejercicio=ejercicio))
//
//    }
}