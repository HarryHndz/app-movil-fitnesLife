package com.harry.fitneslife.adapter.buscadorPersonalizado

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.harry.fitneslife.EjerciciosData.ExerciseResponse
import com.harry.fitneslife.databinding.CardBuscadorBinding
import com.harry.fitneslife.databinding.CardEjercicioBinding

import com.squareup.picasso.Picasso

class BuscadorViewHolder(view:View): RecyclerView.ViewHolder(view) {
    val bindig = CardBuscadorBinding.bind(view)

    //val onClickItem : ((ExerciseResponse)-> Unit)? = null


    fun render(item: ExerciseResponse,onClickListener: (ExerciseResponse)->Unit){
        bindig.nombreEjercicio.text=item.nombre
        Picasso.get().load(item.imagen).into(bindig.imgEjercicio)
        itemView.setOnClickListener { onClickListener(item) }

    }

}