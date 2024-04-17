package com.example.vviiblue.horoscapp.data.network

import com.example.vviiblue.horoscapp.BuildConfig.BASE_URL
import com.example.vviiblue.horoscapp.data.RepositoryImpl
import com.example.vviiblue.horoscapp.data.core.interceptors.AuthInterceptor
import com.example.vviiblue.horoscapp.domain.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/** clase para inyectar un servicio/libreria
 * para que esto sea un Modulo real de Dager Hilt, se le debe agregar la annotation "@Module"
 * se debe agregar otra annotation para definir el alcance que tendra el modulo "@InstallIn()", si quiero
 * que todo el mundo pueda inyectarse esto, se deberia poner "@InstallIn(SingletonComponent::class)" */
@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    /** Ninguna de estas operaciones "provide" las invoco yo, las utiliza dagger hilt*/


    /** Como necestio proveerme de Retrofit,
     * entonces hago lo siguiente (Esto se aplica a cualquier otra libreria o servicio)
     * Esto permite inyectar el objeto retrofit donde me de la gana*/
    @Provides
    @Singleton //para que se cree una sola vez el objeto retrofit, el resto de llamadas devolvera el objeto ya creado
    fun provideRetrofit(okHttpClient: OkHttpClient):Retrofit{ //Devuelvo una instancia del objeto Retrofit
       return Retrofit.Builder()
                      .baseUrl(BASE_URL) // la baseUrl de la api a la que voy a consutar
                      .client(okHttpClient) //El cliente es el "OkHttpClient","OkHttpClient" es la libreria que es parte de las llamadas a internet que permite meter Interceptors y mas cosas...
                      .addConverterFactory(GsonConverterFactory.create())//para convertir los objetos
                      .build()
    }

    /** Los Interceptor se meten dentro de la definicion de Retrofit, mas concretamente en su "OkHttpClient"
     * para ellos como "OkHttpClient" es una "libreria", se define un proveedor, que devuelva el OkHttpClient */
    @Provides
    @Singleton
    fun provideOkHttpClient(authInterceptor: AuthInterceptor):OkHttpClient{
        //Defino el "Interceptor" a meter en el "OkHttpClient",este es el caso del Interceptor agregado por librria"HttpLoggingInterceptor"
        /** tiene varios niveles de log, dependiendo en "level" sera la cantidad de informacion, la informacion se mostrara en LogCat terminal */
        val interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

        /** "authInterceptor" es el "Interceptor" creado de "forma manual" por mi, que tambien se lo agregare al "OkHttpClient" */

        return OkHttpClient
            .Builder()
            .addInterceptor(interceptor)
            .addInterceptor(authInterceptor)
            .build()
    }


    /** Se crea un interfaz ejecutablee de HoroscopeApiService, para inyectar Retrofit en la operacion (ya tengo un provide que
     * que me devuelve un Retrofit "provideRetrofit()")
     * con "provideHoroscopeApiService" Dagger HIlt sabra que existe un provide que prove HoroscopeApiService,
     * por lo que sera posible inyectar "HoroscopeApiService" en una operacion*/
    @Provides
    fun provideHoroscopeApiService(retrofit: Retrofit): HoroscopeApiService {
        return retrofit.create(HoroscopeApiService::class.java)
    }

    /** **************PASOS A SEGUIRIA DAGGER HILT*********************** */
    /**
     * 0) Inyecto el "HoroscopeApiService" en la impelemtacion del repositorio "RepositoryImpl" ->"RepositoryImpl @Inject constructor(private val apiService: HoroscopeApiService)"
     * 1) Cuando se llame a "HoroscopeApiService", Dagger va a intentar invocar "provideHoroscopeApiService(retrofit: Retrofit)" para proveer de forma automatica el "HoroscopeApiService"
     * 2) Dagger se da cuenta que necestia pasar por parametro un objeto "Retrofit", se va a la clase Retrofit y
     *    se fija si tiene "@Inject constructor()", claramente no porque es una clase de libreria, entonces pasa a fijarse si
     *    hay alguna operacion que proveea un "objeto Retrofit" en el modulo (clase con annotation @Module)
     * 3) Dagger en el Modulo se fija si existe algun provide(@Provide) que devuelva un objeto "Retrofit"?, si "provideRetrofit()"
     * 4) Dagger crea el objeto Retrofit usando la operacion "provideRetrofit()" y
     *    ahora si pasa el Retrofit a "provideHoroscopeApiService" y se crea y devuelve "HoroscopeApiService" para ser inyectado en "RepositoryImpl"
     *5) Fin....
     * */

    @Provides
    fun provideRepository(apiService: HoroscopeApiService): Repository {
        return RepositoryImpl(apiService)
    }


}