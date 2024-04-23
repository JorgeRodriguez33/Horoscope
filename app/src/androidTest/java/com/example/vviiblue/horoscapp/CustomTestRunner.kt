package com.example.vviiblue.horoscapp

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner
import dagger.hilt.android.testing.HiltTestApplication

/** Creo mi Runner personalizado, por lo que debo extender de "AndroidJUnitRunner"
 * luego para la configuracion que permita realizar test con Dagger Hilt
 * se debe sobrescribir (override) la funcion "newApplication"
 * y cambiar el valor que se le pasa a "newApplication" de
 * "newApplication(cl, className, context)" ha "newApplication(cl, HiltTestApplication::class.java.name, context)"
 * listo, eso es lo que faltaba para configurar y permitir realizar los test con daggerHilt*/
class CustomTestRunner: AndroidJUnitRunner() {
    override fun newApplication(
        cl: ClassLoader?,
        className: String?,
        context: Context?
    ): Application {
        //return super.newApplication(cl, className, context)
        /** de forma sencilla, se le indica que para lanzar los test, se pase por DaggerHilt antes */
        return super.newApplication(cl, HiltTestApplication::class.java.name, context)
    }
}