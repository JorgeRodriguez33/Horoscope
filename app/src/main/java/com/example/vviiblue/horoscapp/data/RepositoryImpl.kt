package com.example.vviiblue.horoscapp.data

import android.util.Log
import com.example.vviiblue.horoscapp.data.network.HoroscopeApiService
import com.example.vviiblue.horoscapp.domain.Repository
import com.example.vviiblue.horoscapp.domain.model.PredictionModel
import javax.inject.Inject

/** para poder inyectar la clase y utilizar la clase y asi realizar la consulta a la aip, se debe agregar
 *  la annotation de DaggerHit "@Inject constructor()", en este caso, se le pasa un parametro */

/** Para inyectar librerias, se debe crear Modulos(Objetos de Dagger Hilt)
 * en el Modulo se agregan los provider necesarios para que Dagger conozca como inyectar "HoroscopeApiService"*/
class RepositoryImpl @Inject constructor(private val apiService: HoroscopeApiService) : Repository {
    override suspend fun getPrediction(singoDelZodiaco: String): PredictionModel? {
        // Llamar Retrofit, hago la peticion a la api usando Retrofit
        /** runCatching: Es una manera de ejecutar tareas dentro de una especie de "try-catch",
         * se utiliza para ejecutar un bloque de código y
         * capturar cualquier excepción que pueda ocurrir durante su ejecución  */
        runCatching {
            /** Realizo la llamada a internet */
            apiService.getHoroscope(singoDelZodiaco)
        }
            .onSuccess {  // Si la consulta a la api retorno 200
              return  it.toDomain()
            }
            .onFailure {  //Si la consulta a la api fallo
                Log.i("Error consulta getPrediction ", "Ha ocurrido un error ${it.message}")
            }
        return null
    }

}



