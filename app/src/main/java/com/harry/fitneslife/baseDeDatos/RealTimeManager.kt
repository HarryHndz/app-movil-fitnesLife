package com.harry.fitneslife.baseDeDatos

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class RealTimeManager {

    private val databaseReference : DatabaseReference = FirebaseDatabase.getInstance().reference.child("Ejercicios")

    fun getEjercicios() {
        databaseReference.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {

            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }


}