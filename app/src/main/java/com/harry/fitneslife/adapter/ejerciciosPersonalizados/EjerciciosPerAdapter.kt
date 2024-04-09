package com.harry.fitneslife.adapter.ejerciciosPersonalizados

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.harry.fitneslife.EjerciciosData.ExerciseResponse
import com.harry.fitneslife.R

class EjerciciosPerAdapter(private val rutina: ArrayList<ExerciseResponse>,private val onClickListener:(ExerciseResponse)->Unit): RecyclerView.Adapter<EjerciciosPerViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EjerciciosPerViewHolder {
        val layout = LayoutInflater.from(parent.context)
        return EjerciciosPerViewHolder(layout.inflate(R.layout.card_rutina_ejercicio,parent,false))
    }

    override fun getItemCount(): Int {
        return rutina.size
    }

    override fun onBindViewHolder(holder: EjerciciosPerViewHolder, position: Int) {
        val item = rutina[position]
        holder.render(item,onClickListener)
    }
}