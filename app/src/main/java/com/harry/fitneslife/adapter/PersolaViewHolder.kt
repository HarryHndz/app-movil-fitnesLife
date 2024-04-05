package com.harry.fitneslife.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.harry.fitneslife.databinding.CardEjercicioBinding


class PersolaViewHolder(view: View): RecyclerView.ViewHolder(view) {
    val binding = CardEjercicioBinding.bind(view)

    fun render(item: String) {
        binding.nombreEjercicio.text=item
    }
}
