package com.harry.fitneslife.ApiEjercicios

data class Image(
    val created_at: String,
    val id: Int,
    val pivot: Pivot,
    val public_id: String,
    val ulr: String,
    val updated_at: String
)