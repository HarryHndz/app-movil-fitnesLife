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
import android.widget.SearchView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.harry.fitneslife.EjerciciosData.ExerciseResponse
import com.harry.fitneslife.R
import com.harry.fitneslife.adapter.EjerPerAdapter
import com.harry.fitneslife.adapter.ExerciseAdapter
import com.harry.fitneslife.adapter.ejerciciosPersonalizados.EjerciciosPerAdapter
import com.harry.fitneslife.adapter.rutinaAvanzado.AvanzadoAdapter
import com.harry.fitneslife.baseDeDatos.SQLite
import com.harry.fitneslife.baseDeDatos.UserViewFitnexLife
import com.harry.fitneslife.baseDeDatos.UserViewFitnexLife.Companion.userData
import com.harry.fitneslife.databinding.FragmentRutinaPersonalizadaBinding
import com.harry.fitneslife.databinding.FragmentVistaPersonalizadaBinding
import com.harry.fitneslife.funAndClass.Personalizadas

class VistaPersonalizadaFragment : Fragment(), SearchView.OnQueryTextListener, androidx.appcompat.widget.SearchView.OnQueryTextListener {

    private lateinit var dbref : DatabaseReference
    private lateinit var listEjercicios : ArrayList<ExerciseResponse>
    private lateinit var myAdapter : ExerciseAdapter

    private lateinit var dialog : Dialog
    private lateinit var listRutinas: ArrayList<String>
    private val args: VistaPersonalizadaFragmentArgs by navArgs()

    private lateinit var dbrefRutinas : DatabaseReference
    private lateinit var listRutinaEjer : ArrayList<ExerciseResponse>
    private lateinit var ejercicioAdapter: EjerciciosPerAdapter

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
        listEjercicios = arrayListOf()
        listRutinaEjer= arrayListOf()
        iniListener()
        initComponent()
    }

    private fun initComponent() {
        ejercicioAdapter = EjerciciosPerAdapter(listRutinaEjer)

        binding.rvEjerciciosPerso.layoutManager= LinearLayoutManager(
            requireContext(),LinearLayoutManager.VERTICAL,false)

        binding.rvEjerciciosPerso.adapter= ejercicioAdapter

        val registro = getEjercicios()

        if (registro != null && registro.moveToFirst()) {
            do {
                val nombre = registro.getString(2)
                getEjerRu(nombre)
            } while (registro.moveToNext())


        } else {

        }
    }

    private fun getEjerRu(nombreRutina: String) {
        dbrefRutinas = FirebaseDatabase.getInstance().reference.child("Ejercicio")
        val query = dbrefRutinas.orderByChild("nombre").equalTo(nombreRutina)
        query.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (rutinaSnapshot in dataSnapshot.children) {
                    val ejercicioKey = rutinaSnapshot.key

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

    private fun getEjercicios(): Cursor? {
        val itemId = args.itemId
        val idUser = userData.getId()
        Log.i("ciclo", itemId.toString())
        Log.i("ciclo", idUser.toString())
        val con = SQLite(requireContext(), "fitlife", null, 4)
        val dataBase = con.writableDatabase
        val consulta = dataBase.rawQuery( """
            select * from ejercicios as ejer
            inner join rutinasPer as rper on ejer.id_rutina = rper.id_rutina
            where ejer.id_rutina = $itemId
            and rper.user_id = $idUser
        """.trimIndent(), null
        )

        return consulta

        dataBase.close()
    }

    private fun iniListener() {
        binding.fabAddTask.setOnClickListener { showDialog() }
    }

    private fun showDialog() {
        dialog = Dialog(requireContext())
        dialog.setContentView(R.layout.dialog_search_rutina)

        val buscador = dialog.findViewById<androidx.appcompat.widget.SearchView>(R.id.buscador)
        buscador.setOnQueryTextListener(this)

        val rvEjercicios = dialog.findViewById<RecyclerView>(R.id.recycleEjercicios)

        rvEjercicios.layoutManager= GridLayoutManager(
            requireContext(),2)
        myAdapter = ExerciseAdapter(listEjercicios) { ejercicio ->
            onSelectEjercicio(ejercicio)
        }
        rvEjercicios.adapter  = myAdapter

        getDataEjercicio("")

        dialog.show()
    }

    private fun getDataEjercicio(query: String){
        dbref = FirebaseDatabase.getInstance().getReference("Ejercicio")
        if (query.isNotEmpty()){
            dbref.orderByChild("nombre").startAt(query).endAt("${query}/uf8ff").addValueEventListener(object :
                ValueEventListener {
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
            dbref.addValueEventListener(object : ValueEventListener {
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

    fun onSelectEjercicio(ejercicio: ExerciseResponse){
        val nombreEjercicio = ejercicio.nombre
        val itemId = args.itemId

        var con= SQLite(requireContext(), "fitlife", null, 4)
        var dataBase = con.writableDatabase
        var ejer = ContentValues()

        ejer.put("id_rutina",itemId)
        ejer.put("nombre",nombreEjercicio)
        dataBase.insert("ejercicios",null,ejer)

        dataBase.close()
        listRutinaEjer.clear()
        listEjercicios.clear()

        initComponent()
        dialog.hide()

    }

    private fun filterData(query: String) {
        getDataEjercicio(query)
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