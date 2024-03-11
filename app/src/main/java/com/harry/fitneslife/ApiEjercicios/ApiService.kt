package com.harry.fitneslife.ApiEjercicios

import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("/ejercicios")
    fun getEjercicios():Response<ExerciseResponse>
}