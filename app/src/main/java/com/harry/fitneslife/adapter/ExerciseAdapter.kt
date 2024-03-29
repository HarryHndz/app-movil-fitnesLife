package com.harry.fitneslife.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.harry.fitneslife.ApiEjercicios.ExerciseResponseItem
import com.harry.fitneslife.R

class ExerciseAdapter(private val Exercises:List<ExerciseResponseItem>): RecyclerView.Adapter<ExerciseViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseViewHolder {
        var layout = LayoutInflater.from(parent.context)
        return ExerciseViewHolder(layout.inflate(R.layout.card_ejercicio,parent,false))
    }

    override fun getItemCount(): Int {
        return Exercises.size
    }

    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
        val item = Exercises[position]
        holder.render(item)
    }
}