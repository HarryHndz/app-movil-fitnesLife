package com.harry.fitneslife.adapter.cronometro

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.harry.fitneslife.EjerciciosData.ExerciseResponse
import com.harry.fitneslife.R
import com.harry.fitneslife.adapter.rutinaAvanzado.AvanzadoViewHolder

class CronometroAdapter(private val ejercicios: ArrayList<ExerciseResponse>): RecyclerView.Adapter<CronometroViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CronometroViewHolder {
        val layout = LayoutInflater.from(parent.context)
        return CronometroViewHolder(layout.inflate(R.layout.ejer_realizar,parent,false))
    }

    override fun getItemCount(): Int {
        return ejercicios.size
    }

    override fun onBindViewHolder(holder: CronometroViewHolder, position: Int) {
        val item = ejercicios[position]
        holder.render(item)
    }
}