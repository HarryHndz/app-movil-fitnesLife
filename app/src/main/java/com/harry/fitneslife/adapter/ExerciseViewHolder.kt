package com.harry.fitneslife.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.harry.fitneslife.EjerciciosData.ExerciseResponse
import com.harry.fitneslife.databinding.CardEjercicioBinding

import com.squareup.picasso.Picasso

class ExerciseViewHolder(view:View): RecyclerView.ViewHolder(view) {
    val bindig = CardEjercicioBinding.bind(view)

    //val onClickItem : ((ExerciseResponse)-> Unit)? = null


    fun render(item: ExerciseResponse,onClickListener: (ExerciseResponse)->Unit){
        bindig.nombreEjercicio.text=item.nombre
        Picasso.get().load(item.imagen).into(bindig.imgEjercicio)
        itemView.setOnClickListener { onClickListener(item) }

    }

}