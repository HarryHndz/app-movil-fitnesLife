package com.harry.fitneslife.ApiEjercicios

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetroFitHelper {
    private const val URL: String = "http://127.0.0.1:8000/api"
    private val retrofit = Retrofit.Builder().baseUrl(URL).addConverterFactory(GsonConverterFactory.create()).build()


    fun buildRetro(): ApiService {
        return retrofit.create(ApiService::class.java)
    }
}