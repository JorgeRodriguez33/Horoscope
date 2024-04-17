package com.example.vviiblue.horoscapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/** Defino la primer clase que se va a leer de toda la aplicacion, lo que define a esta clase como esa clase... , debe extender de "Application()" */
/** por ultimo de agregarla en el manifest
 *
 * para configurar DaggerHilt se agrega la annotation "HiltAndroidApp"*/
@HiltAndroidApp
class HoroscApp: Application()