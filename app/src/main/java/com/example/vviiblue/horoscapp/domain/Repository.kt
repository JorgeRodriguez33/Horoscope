package com.example.vviiblue.horoscapp.domain

import com.example.vviiblue.horoscapp.domain.model.PredictionModel

/** Cuando el ViewModel le solicite informacion al domain("El horoscopo de Geminis"), pero las capas "domain" y "data"
 *  no deberian "hablar"/tener una comunicacion directa para mantener la "cleanArquitecture", no deberian tener nada en comun */
/** Entonces, para devolverle la informacion que necesita el ViewModel, se realiza atravez de una interfaz*/
/** El repository sera la interfaz que brinde la COMUNICACION entre la capa de Data y la capa de Domain */
/** la implementacion estara en la capa de data "RepositoryImpl" (seria algo asi como un aplicar Proxy)*/
/** de esta forma se pueden hacer proyectos modulares, una capa en un lugar y otra en totalmente otro lugar*/

interface Repository {
    suspend fun getPrediction(sign:String): PredictionModel?
}