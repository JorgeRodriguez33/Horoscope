package com.example.vviiblue.horoscapp.data.providers

import com.example.vviiblue.horoscapp.domain.model.HoroscopeInfo
import com.example.vviiblue.horoscapp.domain.model.HoroscopeInfo.*
import javax.inject.Inject

/** Clase encargada de mandar a domain los hoscopos */
/** para recuperar esta informacion, se puede hacer de 2 formas*/
/** 1° forma) Siguiendo Clean Arquitecture, en la capa de "domain" se puede crear un Caso de Uso,
 *      "Caso de Uso" (es una clase que hace realiza la logica necesaria para devolverle al "View Model" lo que necesita),
 *      en este caso se deberia realizar una clase en domain para devolverle al View Model la lista de horoscopos y mas nada,
 *      no seria correcto solo para esto.
 * 2° forma) Aplicar inyeccion de dependencia (Dagger Hilt), entonces en el ViewModel, solicito un objeto de tipo HoroscopeProvider,
 *      y daggerHilt se encargara de devolverlo en ViewModel para luego ejecutar la operacion definida aqui getHoroscopes(),
 *      PERO para hacer eso, hay que preparar esta clase para que Dagger Hilt la pueda inyectar donde se necesite
 *      por lo que se agrega -- "@Inject constructor()" -- */

class HoroscopeProvider @Inject constructor() {
    fun getHoroscopes():List<HoroscopeInfo>{
        return listOf(
            Aries,
            Taurus,
            Gemini,
            Cancer,
            Leo,
            Virgo,
            Libra,
            Scorpio,
            Sagittarius,
            Capricorn,
            Aquarius,
            Pisces
        )
    }
}