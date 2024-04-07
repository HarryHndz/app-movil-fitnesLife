package com.harry.fitneslife.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import com.harry.fitneslife.R
import com.harry.fitneslife.fragments.RutinaPersonalizadaFragment
import com.harry.fitneslife.funAndClass.Personalizadas


class EjerPerAdapter(private val rutinas: List<Personalizadas>, private val listener: RutinaPersonalizadaFragment):
    RecyclerView.Adapter<PersolaViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersolaViewHolder {
        val layout = LayoutInflater.from(parent.context)
        return PersolaViewHolder(layout.inflate(R.layout.card_perosonalizado, parent, false))
    }

    override fun onBindViewHolder(holder: PersolaViewHolder, position: Int) {
        val item = rutinas[position]
        holder.render(item)
        holder.itemView.setOnClickListener {
            listener.onItemClick(item.id_rutina) // Pasa el ID del elemento clicado al listener
        }
    }

    override fun getItemCount(): Int {
        return rutinas.size
    }

}
