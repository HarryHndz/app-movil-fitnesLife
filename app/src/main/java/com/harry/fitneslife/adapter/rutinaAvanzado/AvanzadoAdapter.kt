package com.harry.fitneslife.adapter.rutinaAvanzado

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.harry.fitneslife.EjerciciosData.ExerciseResponse
import com.harry.fitneslife.R


class AvanzadoAdapter(private val rutina: ArrayList<ExerciseResponse>): RecyclerView.Adapter<AvanzadoViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AvanzadoViewHolder {
        val layout = LayoutInflater.from(parent.context)
        return AvanzadoViewHolder(layout.inflate(R.layout.card_rutina_ejercicio,parent,false))
    }

    override fun getItemCount(): Int {
        return rutina.size
    }

    override fun onBindViewHolder(holder: AvanzadoViewHolder, position: Int) {
        val item = rutina[position]
        holder.render(item)
    }
}