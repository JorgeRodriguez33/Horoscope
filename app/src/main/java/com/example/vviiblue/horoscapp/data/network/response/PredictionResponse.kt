package com.example.vviiblue.horoscapp.data.network.response

import com.example.vviiblue.horoscapp.domain.model.PredictionModel
import com.google.gson.annotations.SerializedName

//@SerializedName("date") no se ofusca, entonces si se ofusca la app, entonces retrofit hara el match con lo que esta en el @SerializedName
/** Por norma general, para testear una operacion, debe estar en los mismos directorios en el directorio "test" porque es una operacion de capa domain entonces es un test unitario
 * entonces para no estar recreando todos los directorios para hacer el test de "PredictionResponse"
 * lo mejor es posicionar el mouse sobre  la operacion "PredictionResponse" y precionar CTRL+SHIFT+T
 * por ultimo dar en crear test, crear*/
data class PredictionResponse (
    @SerializedName("date") val date: String,
    @SerializedName("horoscope") val horoscope: String,
    @SerializedName("sign") val sign: String,
){ // toDomain() es una funcion , que solo funcionara para el tipo de valor "PredictionResponse"
    /**
     *
     * Esto es necesario, para que los modelos de datos no se compartan entre capas, de esta manera lo que se mandara a
     * la capa Domain sera un modelo de datos que pertenece a Domain
     * Esta es una buena practica,
     * Para la capa de data esta bien que el modelo sea "PredictionResponse"
     *pero lara la capa de domain se podria llamar "PredictionModel"
     * Esta operacion simplemente toma lo que obtuvo la capa de data, lo transforma a un modelo de domain paraque el retorn sea valido a la capa domain
     *
     *           Dicho lo anterior, se realiza el Mappeo ,que no es mas que una funcion que convierte el modelo de datos de un sitio(capa)
     *           en el modelo de datos de otro sitio(domain), a eso se le llama Mappear
     * */

    fun toDomain(): PredictionModel{
        return PredictionModel(
            horoscope = horoscope,
            sign = sign
        )
    }
}