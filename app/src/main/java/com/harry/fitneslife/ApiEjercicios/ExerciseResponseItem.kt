package com.harry.fitneslife.ApiEjercicios

data class ExerciseResponseItem(
    val category_id: Int,
    val created_at: String,
    val descripcion: String,
    val detalles: String,
    val ejecucion: String,
    val id: Int,
    val images: List<Image>,
    val nombre: String,
    val preparacion: String,
    val updated_at: Any
)