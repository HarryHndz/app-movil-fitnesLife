package com.harry.fitneslife.adapter.buscadorPersonalizado

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.harry.fitneslife.EjerciciosData.ExerciseResponse
import com.harry.fitneslife.R

class BuscadorAdapter(private val Exercises:ArrayList<ExerciseResponse>, private val onClickListener:(ExerciseResponse)->Unit): RecyclerView.Adapter<BuscadorViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BuscadorViewHolder {
        val layout = LayoutInflater.from(parent.context)
        return BuscadorViewHolder(layout.inflate(R.layout.card_buscador,parent,false))
    }

    override fun getItemCount(): Int {
        return Exercises.size
    }

    override fun onBindViewHolder(holder: BuscadorViewHolder, position: Int) {
        val item = Exercises[position]
        holder.render(item,onClickListener)
    }
}