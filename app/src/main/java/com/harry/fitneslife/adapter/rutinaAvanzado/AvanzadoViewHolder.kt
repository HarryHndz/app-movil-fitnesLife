package com.harry.fitneslife.adapter.rutinaAvanzado

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.harry.fitneslife.EjerciciosData.ExerciseResponse
import com.harry.fitneslife.databinding.CardRutinaEjercicioBinding
import com.squareup.picasso.Picasso

class AvanzadoViewHolder(view:View): RecyclerView.ViewHolder(view) {

    val binding = CardRutinaEjercicioBinding.bind(view)
    fun render(item : ExerciseResponse){
        Picasso.get().load(item.imagen).into(binding.imgEjerRutina)
        binding.txtNombreRut.text = item.nombre
        binding.txtDescripRut.text = item.descripcion
    }
}