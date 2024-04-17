package com.example.vviiblue.horoscapp.data.network

import com.example.vviiblue.horoscapp.data.network.response.PredictionResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface HoroscopeApiService {
    @GET("/{sign}") // {sign} sera el nombre del signo del cual queremos saber la prediccion, {sign} tiene que hacer match con @Path("sign"), tienen que estar exactamente igual escritos
    suspend fun getHoroscope(@Path("sign") sign:String): PredictionResponse // PredictionResponse: es el modelo que contendra la respuesta en la capa "data"
}