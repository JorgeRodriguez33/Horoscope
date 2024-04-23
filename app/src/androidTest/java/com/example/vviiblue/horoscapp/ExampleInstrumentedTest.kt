package com.example.vviiblue.horoscapp

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

/**
 * ESTOS TEST DE "UI" LLEVAN MAS TIEMPO, NO SON COMO LOS TEST UNITARIOS( directorio "test") QUE IMPLICAN SEGUNDOS
 * */

/**
 *
 * AQUI VAN LOS TEST QUE IMPLICAN COSAS DE ANDROID, POR EJEMPLO TESTEAR QUE SE HA IDO DE UNA PANTALLA HA OTRA
 * QUE SE CORROBORE QUE LA NAVEGACION HA SIDO CORRECTA
 * (TODO ESO IMPLICA LANZAR UN INTENT, IMPLICA CAPTURAR UN ACTIVITY)
 *
 * */

@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.vviiblue.horoscapp", appContext.packageName)
    }
}