package com.harry.fitneslife.EjerciciosData




data class ExerciseResponse(
     val descripcion: String? = null,
     val detalles: String? = null,
     val ejecucion: String? = null,
     val imagen: String? = null,
     val nombre: String? = null,
     val preparacion: String? = null
){
     constructor() : this(null,null,null,null,null,null)
}