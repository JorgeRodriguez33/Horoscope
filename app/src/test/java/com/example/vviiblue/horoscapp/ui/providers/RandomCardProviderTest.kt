package com.example.vviiblue.horoscapp.ui.providers

import org.junit.Test
import org.junit.jupiter.api.Assertions.*

class RandomCardProviderTest{

    @Test
    fun `getRandomCard should return a random card`(){
        /**
         * Given - Obtengo un valor a probar
         * When - testeo la accion
         * Then - Compruebo que sea lo que deberia haber pasado
         * */

        //Given
        /** Obtengo la clase que me da la informacion, para hacer la prueba en getLuck*/
        val randomCard = RandomCardProvider()

        //When
        val card = randomCard.getLuck()

        //Then
        /** "assert" es comprobar que algo se cumple, que eso pasa
         * en este caso que el card que me devuelve la operacon que estoy testeando, no devuelva un valor null */
        assertNotNull(card) //assert es una especie de "if" , cuando pase algo...
    }

}