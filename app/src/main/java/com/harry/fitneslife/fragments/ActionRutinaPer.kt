package com.harry.fitneslife.fragments

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.harry.fitneslife.EjerciciosData.ExerciseResponse
import com.harry.fitneslife.R
import com.harry.fitneslife.baseDeDatos.SQLite
import com.harry.fitneslife.baseDeDatos.UserViewFitnexLife.Companion.userData
import com.harry.fitneslife.databinding.FragmentActionRutinaPerBinding
import com.squareup.picasso.Picasso

class ActionRutinaPer : Fragment() {
    private var _binding: FragmentActionRutinaPerBinding? = null
    private lateinit var dbrfe : DatabaseReference
    private val binding get() = _binding!!

    val args: ActionRutinaPerArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentActionRutinaPerBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val nombre : String? = args.nombreEjer
        if (nombre != null) {
            getEjercicio(nombre)
        }

        binding.btnEliminar.setOnClickListener { confirmarDialog() }

    }

    private fun confirmarDialog() {
        val dialog = Dialog(requireContext())
        dialog.setContentView(R.layout.dialog_confirmar)

        dialog.window?.setBackgroundDrawableResource(R.drawable.dialog_border)

        val btnConfirm: Button = dialog.findViewById(R.id.btnConfirmacion)
        val btnDelete: Button = dialog.findViewById(R.id.btnEliminar)

        btnConfirm.setOnClickListener { dialog.hide() }
        btnDelete.setOnClickListener {
            eliminarRutina()
            dialog.hide()
        }

        dialog.show()
    }

    private fun eliminarRutina() {
        val id_rutina = userData.getIdRutina()
        val id_user = userData.getId()
        val nombre : String? = args.nombreEjer
        Log.i("ciclo",id_rutina.toString())
        Log.i("ciclo",id_user.toString())
        if (nombre != null) {
            Log.i("ciclo",nombre)
        }

        val con = SQLite(requireContext(), "fitlife", null, 5)
        val db = con.writableDatabase
        val whereClause = "nombre = ? AND id_rutina IN (SELECT id_rutina FROM rutinasPer WHERE user_id = ? AND id_rutina = ?)"
        val whereArgs = arrayOf(nombre ,id_user.toString(), id_rutina.toString())

        db.delete("ejercicios", whereClause, whereArgs)

        db.close()

        val fragmentManager = getFragmentManager()

// Verificar si hay fragmentos en la pila de retroceso
        if (fragmentManager != null) {
            if (fragmentManager.backStackEntryCount > 0) {
                // Retroceder al fragmento anterior
                fragmentManager.popBackStack()
            } else {
                // No hay fragmentos en la pila de retroceso
                // Manejar según sea necesario (por ejemplo, cerrar la actividad)
                // Esto puede variar dependiendo de cómo quieras manejar este caso
            }
        }
    }


    private fun getEjercicio(nombre: String){
        dbrfe = FirebaseDatabase.getInstance().getReference("Ejercicio")
        val query = dbrfe.orderByChild("nombre").equalTo(nombre)
        query.addValueEventListener(object: ValueEventListener {
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