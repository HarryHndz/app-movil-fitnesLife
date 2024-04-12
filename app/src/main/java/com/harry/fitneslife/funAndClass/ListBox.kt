package com.harry.fitneslife.funAndClass


import androidx.core.content.ContextCompat.getString
import com.harry.fitneslife.R

class ListBox{
    companion object{

        val carrusel = listOf<HomeBox>(
            HomeBox("Cardio" , R.drawable.ic_cardio),
            HomeBox("Exercises",R.drawable.ic_exercise),
            HomeBox("Routines",R.drawable.ic_routine),
            HomeBox("Body",R.drawable.ic_body)
        )


    }
}