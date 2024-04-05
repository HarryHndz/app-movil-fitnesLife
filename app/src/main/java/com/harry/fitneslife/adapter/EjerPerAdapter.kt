package com.harry.fitneslife.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.harry.fitneslife.R

class EjerPerAdapter(private val rutinas: MutableList<String>):
    RecyclerView.Adapter<PersolaViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersolaViewHolder {
        val layout = LayoutInflater.from(parent.context)
        return PersolaViewHolder(layout.inflate(R.layout.card_ejercicio, parent, false))
    }

    override fun onBindViewHolder(holder: PersolaViewHolder, position: Int) {
        val item = rutinas[position]
        holder.render(item)
    }

    override fun getItemCount(): Int {
        return rutinas.size
    }

}
