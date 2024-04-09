package com.harry.fitneslife.adapter.ejerciciosPersonalizados

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.harry.fitneslife.EjerciciosData.ExerciseResponse
import com.harry.fitneslife.databinding.CardRutinaEjercicioBinding
import com.squareup.picasso.Picasso

class EjerciciosPerViewHolder(view:View): RecyclerView.ViewHolder(view) {

    val binding = CardRutinaEjercicioBinding.bind(view)
    fun render(item : ExerciseResponse,onClickListener: (ExerciseResponse)->Unit){
        Picasso.get().load(item.imagen).into(binding.imgEjerRutina)
        binding.txtNombreRut.text = item.nombre
        binding.txtDescripRut.text = item.descripcion
        itemView.setOnClickListener { onClickListener(item) }
    }
}