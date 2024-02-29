package com.harry.fitneslife.funAndClass

import com.harry.fitneslife.R

class ListBox{
    companion object{
        val carrusel = listOf<HomeBox>(
            HomeBox((R.string.cardio), R.drawable.ic_cardio),
            HomeBox(R.string.ejercicio,R.drawable.ic_exercise),
            HomeBox(R.string.rutina,R.drawable.ic_routine),
            HomeBox(R.string.body,R.drawable.ic_body)
        )


    }
}