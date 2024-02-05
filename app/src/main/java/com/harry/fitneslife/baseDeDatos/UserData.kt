package com.harry.fitneslife.baseDeDatos

import android.content.Context

class UserData(val context: Context) {
    val SHARED_NAME = "DatosUsuarios"
    val SHARED_EMAIL = "correo"
    val SHARED_USER_NAME = "nombre"

    val storage = context.getSharedPreferences(SHARED_NAME, 0)

    fun saveNombre(name:String) {
        storage.edit().putString(SHARED_USER_NAME, name).apply()
    }

    fun saveEmail(email:String) {
        storage.edit().putString(SHARED_EMAIL, email).apply()
    }

    fun getName():String {
        return storage.getString(SHARED_USER_NAME, "")!!
    }

    fun getEmail():String {
        return storage.getString(SHARED_EMAIL, "")!!
    }

    fun wipe() {
        storage.edit().clear().apply()
    }
}