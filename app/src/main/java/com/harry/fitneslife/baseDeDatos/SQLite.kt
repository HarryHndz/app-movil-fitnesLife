package com.harry.fitneslife.baseDeDatos

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SQLite(
    context: Context?,
    name: String?,
    factory: SQLiteDatabase.CursorFactory?,
    version: Int
) : SQLiteOpenHelper(context, name, factory, version) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("create table usuarios (user_id INTEGER primary key autoincrement," +
                "nombre text" +
                ", correo text" +
                ", contraseña text" +
                ", imc double)")
        db?.execSQL("create table rutinasPer (id_rutina INTEGER primary key autoincrement, " +
                "user_id INTEGER," +
                "nombre text," +
                "foreign key (user_id) references usuarios(user_id))")
        db?.execSQL("create table ejercicios (rutina_id INTEGER primary key autoincrement, " +
                "id_rutina INTEGER," +
                "nombre text," +
                "foreign key (id_rutina) references rutinasPer(id_rutina))")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS usuarios")
        onCreate(db)
    }
}