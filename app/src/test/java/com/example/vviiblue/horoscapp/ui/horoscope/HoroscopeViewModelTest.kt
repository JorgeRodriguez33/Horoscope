package com.example.vviiblue.horoscapp.ui.horoscope

import com.example.vviiblue.horoscapp.data.providers.HoroscopeProvider
import com.example.vviiblue.horoscapp.motherobject.HoroscopeMotherObject.horoscopeInfoList
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions.*

class HoroscopeViewModelTest{

    /** creo una instancia del "HoroscopeProvider" Mockeado, para
     * asi pasar un HoroscopeProvider falso, que me sea util para comprobar /testear otras cosas
     * Recordar: Lo que mockeo no es lo que estoy testeando!!! simplemente son una cosa que me ayuda con el test de algo mas*/
    // esto depende de la libreria "mockk" // puede darse el caso que HoroscopeProvider tenga muchos metodos pero a
    // mi solo me interesa testear getHoroscopes(), entonces para si aqui pongo al lado de
    // la annotation "@MockK(relaxed = true)" con esto ninguna operacion se va a quejar por
    // no haber una respuesta para los metodos, esto es peligroso porque puede dar lugar a que queden operaciones
    // sin testear
    @MockK
    lateinit var horoscopeProvider: HoroscopeProvider

    private lateinit var viewModel:HoroscopeViewModel

    /** ANTES DE LANZAR LOS TEST Y LUEGO DE LANZAR LOS TEST
     * SE PUEDEN REALIZAR CIERTAS OPERACIONES
     * para hacer eso
     * generalmente se crea una funcion
     * llamada "setUp()" con una Annotation "@Before" para que se lance ANTES de un test
     *  y con la Annotation "@After" para que se lance DESPUES de un test
     *  generalmente esto es util si mas de una operacion de test tienen logica compartida
     *  por ejemplo inicializar una clase para hacer algo
     *  */

    // Esta operacion es para hacer cosas que tengamos que hacer antes...
    @Before
    fun setUp(){
        /**Abajo de la calse hay una expliccion de esto */
        // Para que los MockK funcione, se debe hacer una configuracion ANTES de empezar los test
        MockKAnnotations.init(this, relaxUnitFun = true)
    }

    /**
     *
     *  Voy a testear que cuando este ViewModel "HoroscopeViewModel" se cree,
     *  le asigne el horoscopo que ha cargado
     *  "horoscopeProvider.getHoroscopes()"
     *  En resumidas cuentas, cuando se cargue el viewodel siempre cargue un horoscopo
     */
    @Test
    fun `when viewmodel is created then horoscopes are loaded`(){
        //Given
        /** esto solamente "horoscopeProvider.getHoroscopes()" va a generar un error porque "horoscopeProvider" es falso
         * por lo que ademas, debo "mockKear una respuesta", entonces para eso hago lo siguiente
         * que basicamente quiere decir : "Cada vez que alguien llame a horoscopeProvider.getHoroscopes(),
         * se va a devolver "horoscopeInfoList" que es una respuesta predefinida ya en motherObject"*/
         every { horoscopeProvider.getHoroscopes() } returns horoscopeInfoList
        // si la operacion "getHoroscopes()" usara corrutina, entonces la respuesta MockKeada deberia ser "coEvery"

        /** inicializo el viewModel, y obtengo su instancia */
        viewModel = HoroscopeViewModel(horoscopeProvider) // el "HoroscopeViewModel" si es de verdad, el "horoscopeProvider" es el que es falso/el mockeado

        //When
        /** obtengo el horoscopo que tiene el viewModel asignado (deberia tener uno por defecto) */
        val horoscopes = viewModel.horoscope.value

        //Then
        /** Compruebo que tiene Horoscopo guardado el viewModel */
        assertTrue(horoscopes.isNotEmpty())
    }

}


/** ****************EXPLICACION metodo setUp() y su contenido************************/
/**
 * La función setUp() se encarga de inicializar y configurar el entorno de prueba. En este caso,
 * se utiliza MockKAnnotations.init() para inicializar el entorno de pruebas con MockK.
 *
 * La función MockKAnnotations.init() toma dos parámetros:
 *
 *     this: Esto hace referencia al objeto actual (en este caso, la clase de prueba o el contexto de prueba).
 *     relaxUnitFun = true: Este parámetro indica que se permitirá relajar (ignorar) las
 *     llamadas a funciones unitarias en los objetos simulados (mocks) dentro de las pruebas.
 *     Esto significa que si alguna función de un objeto simulado no está especificada en la prueba y
 *     es llamada durante la ejecución de la prueba, MockK no generará un error y simplemente la ignorará.
 *
 * En resumen, esta configuración se asegura de que el entorno de prueba esté preparado correctamente para
 * utilizar MockK como framework de pruebas y permite relajar la implementación de
 * funciones unitarias en los objetos simulados para simplificar las pruebas.
 *
 * */