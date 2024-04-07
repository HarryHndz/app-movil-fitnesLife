package com.harry.fitneslife.baseDeDatos

import android.content.Context

class UserData(val context: Context) {
    val SHARED_NAME = "DatosUsuarios"
    val SHARED_EMAIL = "correo"
    val SHARED_USER_NAME = "nombre"
    val SHARED_IMC = "imc"
    val SHARED_ID = "user_id"

    val storage = context.getSharedPreferences(SHARED_NAME, 0)

    fun saveNombre(name:String) {
        storage.edit().putString(SHARED_USER_NAME, name).apply()
    }

    fun saveEmail(email:String) {
        storage.edit().putString(SHARED_EMAIL, email).apply()
    }

    fun saveId(id:Int) {
        storage.edit().putInt(SHARED_ID, id).apply()
    }

    fun saveImc(imc:String) {
        storage.edit().putString(SHARED_IMC, imc).apply()
    }

    fun getName():String {
        return storage.getString(SHARED_USER_NAME, "")!!
    }

    fun getEmail():String {
        return storage.getString(SHARED_EMAIL, "")!!
    }

    fun getId():Int {
        return storage.getInt(SHARED_ID, -1)!!
    }

    fun getImc():String {
        return storage.getString(SHARED_EMAIL, "")!!
    }

    fun wipe() {
        storage.edit().clear().apply()
    }
}