package com.harry.fitneslife.baseDeDatos

import android.app.Application

class UserViewFitnexLife:Application() {

    companion object{
        lateinit var userData: UserData
    }

    override fun onCreate() {
        super.onCreate()
        userData = UserData(applicationContext)
    }
}