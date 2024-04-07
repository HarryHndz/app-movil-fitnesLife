package com.harry.fitneslife.adapter.rutinaIntermedio

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.harry.fitneslife.EjerciciosData.ExerciseResponse
import com.harry.fitneslife.R


class IntermedioAdapter(private val rutina : ArrayList<ExerciseResponse>): RecyclerView.Adapter<IntermedioViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IntermedioViewHolder {
        val layout = LayoutInflater.from(parent.context)
        return IntermedioViewHolder(layout.inflate(R.layout.card_rutina_ejercicio,parent,false))
    }

    override fun getItemCount(): Int {
        return rutina.size
    }

    override fun onBindViewHolder(holder: IntermedioViewHolder, position: Int) {
        val item = rutina[position]
        holder.render(item)
    }
}