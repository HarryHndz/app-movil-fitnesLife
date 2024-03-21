package com.harry.fitneslife.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.harry.fitneslife.databinding.CardEjercicioBinding
import com.squareup.picasso.Picasso

class ExerciseViewHolder(view:View): RecyclerView.ViewHolder(view) {
    val bindig = CardEjercicioBinding.bind(view)

    fun render(item: String){
        /*bindig.nombreEjercicio.text=item.nombre*/
        Picasso.get().load(item).into(bindig.imgEjercicio)
    }

}