package com.example.vviiblue.horoscapp.data.core.interceptors

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

/** El directorio "core" tendra todas las cosas genericas que se puedan usar en la capa de "data" */
/** Entonces dentro del directorio de cosas genericas se hace un directorio solo para los interceptors*/

/** A modo de ejemplo, se pone en la situacion de por ejemplo esta es una aplicacion
 * que tiene Login, despues de ese login el servidor te daria un token, entonces a cada llamada al servidor
 * se le deberia pasar el "token" que te dio el servidor, y ese token se tiene que mandar en la cabecera de la llamada (Header)
 * sea una consulta para obtener la prediccion del zodiaco o para crear un nuevo usuario...
 * el "token" es un codigo de validacion, que confirma que yo soy el usuario que se acaba de loguear a la app
 * asi que... un ejemplo de "Interceptor" para ese caso seria "AuthInterceptor", que se creeara de forma manual*/
class AuthInterceptor @Inject constructor(private val tokenManager: TokenManager) : Interceptor { // le agrego el "@Inject constructor" para poder inyectarla luego en el "NetworkModule"
    override fun intercept(chain: Interceptor.Chain): Response {
        // esta "chain: Interceptor.Chain" es la llamada que va hacer retrofit ,
        // (chain.request() es justo la peticion al servidor), yo la obtengo y le agrego el "header" y
        // por ultimo la devuelvo como "Response" para que siga su camino al servidor
        val request =  chain
                              .request()
                              .newBuilder() /** "newBuilder" = "vuelvete a crear sin perder la informacion, pero con algo nuevo (el header, en este caso) " */
                              .header("Autorization",tokenManager.getToken()) //header( clave = "Autorization", valor = tokenManager.getToken() )
                              .build()

        return chain.proceed(request)
    }

}

/** esta calse deberia estar en otro sitio, esta aqui para cumplir con el ejemplo */
class TokenManager @Inject constructor(){
    fun getToken():String = "Esto es una prueba!!!"
}