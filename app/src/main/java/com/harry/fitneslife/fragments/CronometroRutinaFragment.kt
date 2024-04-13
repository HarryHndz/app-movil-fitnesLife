package com.harry.fitneslife.fragments

import android.database.Cursor
import android.os.Bundle
import android.os.CountDownTimer
import android.os.SystemClock
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.harry.fitneslife.EjerciciosData.ExerciseResponse
import com.harry.fitneslife.adapter.cronometro.CronometroAdapter
import com.harry.fitneslife.baseDeDatos.SQLite
import com.harry.fitneslife.baseDeDatos.UserViewFitnexLife
import com.harry.fitneslife.baseDeDatos.UserViewFitnexLife.Companion.userData
import com.harry.fitneslife.databinding.FragmentCronometroRutinaBinding

class CronometroRutinaFragment : Fragment() {
    private var _binding: FragmentCronometroRutinaBinding? = null
    private val bindig get() = _binding!!
    private var elapsedTime = 0L
    private lateinit var countDownTimer: CountDownTimer
    private var pausar = false
    private lateinit var dbrefRutinas : DatabaseReference
    private lateinit var listRutinaEjer : ArrayList<ExerciseResponse>
    private lateinit var ejercicioAdapter:CronometroAdapter
    val args:CronometroRutinaFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCronometroRutinaBinding.inflate(inflater, container, false)
        return bindig.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listRutinaEjer = arrayListOf()

        init()
    }
    private fun init() {
        ejercicioAdapter = CronometroAdapter(listRutinaEjer)
        bindig.reciEjerRunC.layoutManager = LinearLayoutManager(
            requireContext(), LinearLayoutManager.HORIZONTAL,false
        )
        bindig.reciEjerRunC.adapter =ejercicioAdapter
        val tipoRutinaEjer: String? = args.nombreEjercicio

        if (tipoRutinaEjer != null) {
            /*getRutinaEjercicio(tipoRutinaEjer)*/
            if (getRutinaEjercicio(tipoRutinaEjer)) {
                Log.i("Vamos", "Obtencion de rutinas correctas de firebase")
            } else {
                Log.i("Vamos", "Intento de hacerlo con sqlite")
                val registro = getEjercicios(tipoRutinaEjer.toInt())

                if (registro != null && registro.moveToFirst()) {
                    do {
                        val nombre = registro.getString(2)
                        getEjerRu(nombre)
                    } while (registro.moveToNext())


                }
            }
        }
        bindig.btnCronometro.setOnClickListener {

            if (!pausar){
                bindig.cronometro.base = SystemClock.elapsedRealtime() - elapsedTime
                bindig.cronometro.start()
                bindig.desCrono.base = SystemClock.elapsedRealtime()
                pausar = true
            }else{
                bindig.cronometro.stop()
                elapsedTime = SystemClock.elapsedRealtime() - bindig.cronometro.base

                countDownTimer = object : CountDownTimer(20000,1000){
                    override fun onTick(millisUntilFinished: Long) {
                        val progress = ((20000 - millisUntilFinished) / 190).toInt()
                        bindig.pbTimer.progress = progress
                        bindig.desCrono.text = (millisUntilFinished / 1000).toString()
                        bindig.btnCronometro.isEnabled = false
                    }

                    override fun onFinish() {
                        bindig.desCrono.text = "¡Ha terminado el descanso!"
                        bindig.cronometro.base = SystemClock.elapsedRealtime() - elapsedTime
                        bindig.cronometro.start()
                        bindig.btnCronometro.isEnabled = true
                    }
                }
                countDownTimer.start()
                pausar = true
            }
        }
    }

    private fun getRutinaEjercicio(tipoRutinaEjer: String): Boolean {
        if (tipoRutinaEjer.toIntOrNull() !=null) {
            Log.i("vamos", "A ver que pasa $tipoRutinaEjer")
            return false
        } else {
            dbrefRutinas = FirebaseDatabase.getInstance().reference.child("RutinasEjercicios").child(tipoRutinaEjer)
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
        return true
    }

    private fun getEjercicios(id: Int): Cursor? {
        val itemId = id
        val idUser = userData.getId()
        Log.i("Vamos", "Entro a sqlite")
        val con = SQLite(requireContext(), "fitlife", null, 5)
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

    override fun onDestroy() {
        super.onDestroy()
        if (::countDownTimer.isInitialized) {
            countDownTimer.cancel()
        }
        _binding = null
    }
}