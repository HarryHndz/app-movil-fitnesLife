package com.harry.fitneslife.adapter.cronometro


import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.harry.fitneslife.EjerciciosData.ExerciseResponse
import com.harry.fitneslife.databinding.EjerRealizarBinding
import com.squareup.picasso.Picasso

class CronometroViewHolder(view: View): RecyclerView.ViewHolder(view) {

    val binding = EjerRealizarBinding.bind(view)
    fun render(item: ExerciseResponse){
        binding.textEjerRu.text = item.nombre
        Picasso.get().load(item.imagen).into(binding.imgEjerRu)

    }


}