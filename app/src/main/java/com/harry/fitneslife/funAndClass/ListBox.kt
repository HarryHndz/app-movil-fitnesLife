package com.harry.fitneslife.funAndClass

import com.harry.fitneslife.R

class ListBox{
    companion object{
        val carrusel = listOf<HomeBox>(
            HomeBox("getString(R.string.resuelva)", R.drawable.ic_cardio),
            HomeBox("getString(R.string.ejercicio)",R.drawable.ic_exercise),
            HomeBox("getString(R.string.rutina)",R.drawable.ic_routine),
            HomeBox("getString(R.string.body)",R.drawable.ic_body)
        )


    }
}