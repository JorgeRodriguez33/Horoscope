package com.example.vviiblue.horoscapp.motherobject

import com.example.vviiblue.horoscapp.data.network.response.PredictionResponse
import com.example.vviiblue.horoscapp.domain.model.HoroscopeInfo

/**
 * MotherObject:
 * un MotherObject es un "OBJECT" que tiene todos los modelos basicos que se necesitan para hacer las pruebas de
 * test
 *
 * de esta manera no hay que estar creando por ejemplo "PredictionResponse("date","prediction","taurus")"
 * en cada test que se necesite un "PredictionResponse"
 *
 * de esta manera si hay que cambiar algun modelo para la prueba, se cambia en un solo lugar y no en cada test
 *
 * */
object HoroscopeMotherObject {

    val anyResponse = PredictionResponse("date", "prediction", "taurus")

    val horoscopeInfoList = listOf(
        HoroscopeInfo.Aries,
        HoroscopeInfo.Taurus,
        HoroscopeInfo.Gemini,
        HoroscopeInfo.Cancer,
        HoroscopeInfo.Leo,
        HoroscopeInfo.Virgo,
        HoroscopeInfo.Libra,
        HoroscopeInfo.Scorpio,
        HoroscopeInfo.Sagittarius,
        HoroscopeInfo.Capricorn,
        HoroscopeInfo.Aquarius,
        HoroscopeInfo.Pisces
    )

}