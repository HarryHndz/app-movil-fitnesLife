package com.harry.fitneslife.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.harry.fitneslife.databinding.CardEjercicioBinding
import com.harry.fitneslife.databinding.CardPerosonalizadoBinding
import com.harry.fitneslife.funAndClass.Personalizadas


class PersolaViewHolder(view: View): RecyclerView.ViewHolder(view) {
    val binding = CardPerosonalizadoBinding.bind(view)

    fun render(item: Personalizadas) {
        binding.nombrePersonalizado.text= item.nombre
    }
}
