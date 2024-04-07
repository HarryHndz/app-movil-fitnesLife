package com.harry.fitneslife.adapter.rutinaPrincipiante

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.harry.fitneslife.EjerciciosData.ExerciseResponse
import com.harry.fitneslife.EjerciciosData.RutinaResponse
import com.harry.fitneslife.R
import com.harry.fitneslife.adapter.ExerciseViewHolder

class PrincipianteAdapter(private val rutinas : ArrayList<ExerciseResponse>): RecyclerView.Adapter<PrincipianteViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PrincipianteViewHolder {
        val layout = LayoutInflater.from(parent.context)
        return PrincipianteViewHolder(layout.inflate(R.layout.card_rutina_ejercicio,parent,false))
    }
    override fun getItemCount(): Int {
        return rutinas.size
    }
    override fun onBindViewHolder(holder: PrincipianteViewHolder, position: Int) {
        val item = rutinas[position]
        holder.render(item)
    }
}