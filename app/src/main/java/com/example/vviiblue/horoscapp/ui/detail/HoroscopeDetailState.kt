package com.example.vviiblue.horoscapp.ui.detail

import com.example.vviiblue.horoscapp.domain.model.HoroscopeModel

/** Clase que gestiona los estados del Flow
 * Segun el estado, la pantalla sabra lo que se tiene que hacer
 * */
sealed class HoroscopeDetailState {
    /** Cuando es un estado sencillo que no requiere parametros como por ejemplo Loading, se usa "data object" */
    data object Loading:HoroscopeDetailState()

    /** ¡¡¡¡ Cuando hay que pasarle parametros va a ser una "data class" !!!! */
    data class Error(val errorMsg:String):HoroscopeDetailState()

    data class Success(val prediccion:String,val sign:String, val horoscopeModel: HoroscopeModel):HoroscopeDetailState()
}