package com.harry.fitneslife.funAndClass


import android.content.res.Resources
import com.harry.fitneslife.R

class ListBox{
    companion object{

        val carrusel = listOf<HomeBox>(
            HomeBox("Cardio", R.drawable.ic_cardio),
            HomeBox("Ejercicios",R.drawable.ic_exercise),
            HomeBox("Rutinas",R.drawable.ic_routine),
            HomeBox("Cuerpo",R.drawable.ic_body)
        )

    }
}