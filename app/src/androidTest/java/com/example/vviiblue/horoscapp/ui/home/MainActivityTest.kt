package com.example.vviiblue.horoscapp.ui.home

import android.content.Intent
import androidx.fragment.app.testing.FragmentScenario
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.runner.AndroidJUnit4
import com.example.vviiblue.horoscapp.R
import com.example.vviiblue.horoscapp.ui.detail.HoroscopeDetailActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
 * Si sale este error, hay que hacer lo siguiente
 * java.lang.IllegalStateException: Hilt test, com.example.vviiblue.horoscapp.ui.home.MainActivityTest,
 * cannot use a @HiltAndroidApp application but found com.example.vviiblue.horoscapp.HoroscApp. To fix,
 * configure the test to use HiltTestApplication or a custom Hilt test application generated with @CustomTestApplication.
 *
 * -->
 * Se debe crear la clase "CustomTestRunner" y esa clase sera la que hara de lanzador,
 * se debera especificara en el Build.gradle de "app" de la siguiente manera
 * testInstrumentationRunner = "com.example.vviiblue.horoscapp.CustomTestRunner"
 *
 * */

@RunWith(AndroidJUnit4::class) //Le indico que los test se deben correr con "AndroidJUnit4"
@HiltAndroidTest //Annotation necesaria para realiza los test con DaggerHilt
class MainActivityTest{

    /** Las reglas (Rule) son configuraciones necesarias que se deben meter en la pantalla para realizar el test en la ui
     *  las reglas se les puede especificar un orden en que se van a ir ejecutando,
     *  como se va a testear tambien las inyecciones con "DaggerHilt", entonces se agrega en el orden
     *  para que la primera regla sea la de DaggerHilt, es imprensendible*/
    // Con esto se prepara, para que la clase pueda ser "inyectada" o inyectar cosas
    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    /** Para configurar a que Activity se le va a realizar el test, en este caso hacer que se haga test del Activity Main
     *  se debe especificar en la siguiente Regla
     *  Aqui se define el escenario del test, IMPORTANTE "para los Fragment es igual" se le indica por medio de "FragmentScenario(elFragment)"
     *  tener cuidado, porque los que llevan Rule "ActivityScenarioRule" al final se definen en las reglas,
     *  los que llevan solo "ActivityScenario", es para definir los escenarios de test en los metodos con el Annotation "@Before"
     *  ejemplo fun setUp()*/
    @get:Rule(order=1)
    val mainActivityRule = ActivityScenarioRule(MainActivity::class.java)


    /** Realizo las configuraciones antes de lanzar el test, en este caso se inyecta daggerHilt */
    @Before
    fun setUp(){
        hiltRule.inject() //se inyecta daggerHilt
        //preparo los Intents, para el Test que comprueba que si doy click en un item del recycleView,
        // el Intent se carga con un activity que tiene como nombre HoroscopeDetailActivity
        Intents.init()
    }

    @After
    fun tearDown(){
        /** importante hacer esto!!!!!!!!, para liberar la carga e los intents luego de correr los test y
         *  asi evitar fallos de test */
        Intents.release()
    }

    /** En los test de UI no se pueden poner los nombres de las operaciones con las palabras separadas, se debe usar "_" */
    /** Testeo que cuando la activity se lance vaya a la BottomBar y seleccione el Fragment "luckFragment"*/
    @Test
    fun when_mainactivity_is_created_then_open_luck_fragment() {
        /** "onView" = En la vista que yo te diga , busca por la Id "withId", la vista que tenga esta referencia (R.id.luckFragment),
         * y a esa vista, vas hacer una accion "perform", que accion? vas hacer "click" sobre esa vista*/
        onView(withId(R.id.luckFragment)).perform(click())

        /** withId no es la unica forma de buscar la vista, se puede buscar por context, por un Tag por el nombre
         * del recurso de la imagen que esta cargando... */
    }


    /** Testeo que cuando se haga cick en un Horoscopo, se vaya al activity Detail ,
     * estos tipos de test, son muy utiles para saber si la navegacion es correcta ante cierta accion*/
    @Test
    fun when_horoscope_is_selected_then_open_detail() {
        /** "onView" = En la vista que yo te diga , busca por la Id "withId", la vista que
         *  tenga esta referencia (R.id.rvHoroscope),
         *  entonces realizo un perfom sobre el recycleview, como es un "recycleview", no puedo simplemente hacer click
         *  debo hacer click en uno de los items del "recycleview",
         *  entonces especifico que para ese Recyclview se genere una "accion de RecycleView" para el item que esta en
         *  la posicion 3, y dicha accion que se le va a hacer a ese item sera "hacer un click"*/
        //When
        onView(withId(R.id.rvHoroscope)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                3,
                click()
            )
        )

        //then
        /** Se testea el Intent, que es el que tiene la informacion hacia donde va a navegar,
         * asi que compruebo que cuando yo doy click en la posicion 3 del "recycleView",
         * se cargue un Intent con esta informacion "HoroscopeDetailActivity::class.java.name" */
        intended(hasComponent(HoroscopeDetailActivity::class.java.name))

        /** **** Tip ********¡¡¡¡¡¡ IMPORTANTE  !!!!!!!!************ Tip **** */
        /** como la navegacion a HoroscopeDetailActivity implica una animacion antes de pasar a la activity, el test eso
         * no lo espera y falla , para solucionar esto, hay que DESACTIVAR LAS ANIMACIONES DEL DISPOSITIVO donde
         * se esta corriendo los test, desde LA PANTALLA DE DESSARROLLADOR y DESACTIVAR
         * Windows Animation scale
         * Transition animation scale
         * Animator duration scale
         * las 3 deben estar en off
         * luego de hacer eso, el test se hara de forma correcta*/

    }


}