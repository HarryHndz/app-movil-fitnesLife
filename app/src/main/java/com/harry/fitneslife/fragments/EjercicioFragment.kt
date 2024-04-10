package com.harry.fitneslife.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
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


class EjercicioFragment : Fragment(), SearchView.OnQueryTextListener {

    private lateinit var dbref : DatabaseReference
    private lateinit var listEjercicios : ArrayList<ExerciseResponse>
    private var _bindig : FragmentEjercicioBinding? = null
    private lateinit var myAdapter : ExerciseAdapter
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
        binding.buscador.setOnQueryTextListener(this)
        init()
        getDataEjercicio("")

    }
    private  fun init(){
        binding.recycleEjercicios.layoutManager= GridLayoutManager(
            requireContext(),2)
        myAdapter = ExerciseAdapter(listEjercicios) { ejercicio ->
            onSelectEjercicio(ejercicio)
        }
        binding.recycleEjercicios.adapter  = myAdapter
    }
    private fun getDataEjercicio(query: String){
        dbref = FirebaseDatabase.getInstance().getReference("Ejercicio")
        if (query.isNotEmpty()){
            dbref.orderByChild("nombre").startAt(query).endAt("${query}/uf8ff").addValueEventListener(object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    Log.d("firebase","llamada exitosa")
                    listEjercicios.clear()
                    if (snapshot.exists()){
                        for(EjercicioSnapshot in snapshot.children){
                            Log.d("data","la data es ${EjercicioSnapshot.key}")
                            val ejer = EjercicioSnapshot.getValue(ExerciseResponse::class.java)
                            ejer?.let {
                                listEjercicios.add(it)
                            }
                        }
                        myAdapter.notifyDataSetChanged()
                    }
                }
                override fun onCancelled(error: DatabaseError) {
                    Log.w("firebase", "Failed to read value.", error.toException())
                }
            })
        } else {
            // Si no hay consulta, obtenemos todos los datos
            dbref.addValueEventListener(object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    Log.d("firebase","llamada exitosa")
                    listEjercicios.clear()
                    if (snapshot.exists()){
                        for(EjercicioSnapshot in snapshot.children){
                            val ejer = EjercicioSnapshot.getValue(ExerciseResponse::class.java)
                            ejer?.let {
                                listEjercicios.add(it)
                            }
                        }
                        myAdapter.notifyDataSetChanged()
                    }
                }
                override fun onCancelled(error: DatabaseError) {
                    Log.w("firebase", "Failed to read value.", error.toException())
                }
            })
        }
    }

    private fun filterData(query: String) {
        getDataEjercicio(query)
    }
    fun onSelectEjercicio(ejercicio: ExerciseResponse){
        val nombreEjercicio = ejercicio.nombre
        findNavController().navigate(EjercicioFragmentDirections.actionNavEjerciciosToDetailExerFragment(nombreEjer = nombreEjercicio))
    }
    override fun onQueryTextSubmit(query: String?): Boolean {
        if (!query.isNullOrEmpty()){
            filterData(query)
        }
        return true
    }
    override fun onQueryTextChange(newText: String?): Boolean {
        newText?.let { filterData(it) }
        return true
    }
}