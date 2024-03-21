package com.harry.fitneslife.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.harry.fitneslife.EjerciciosData.ExerciseResponse
import com.harry.fitneslife.databinding.CardEjercicioBinding

class ExerciseViewHolder(view:View): RecyclerView.ViewHolder(view) {
    val bindig = CardEjercicioBinding.bind(view)

    fun render(item: ExerciseResponse){
        bindig.nombreEjercicio.text=item.nombre
        bindig.descripcionEjercicio.text = item.apellido
        bindig.EdadEjercicio.text = item.edad.toString()
//        Picasso.get().load(item.images[4]).into(bindig.imgEjercicio)
    }

}