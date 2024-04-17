package com.example.vviiblue.horoscapp.ui.horoscope

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.vviiblue.horoscapp.databinding.FragmentHoroscopeBinding
import com.example.vviiblue.horoscapp.domain.model.HoroscopeInfo
import com.example.vviiblue.horoscapp.domain.model.HoroscopeInfo.*
import com.example.vviiblue.horoscapp.domain.model.HoroscopeModel
import com.example.vviiblue.horoscapp.ui.horoscope.adapter.HoroscopeAdapter
import com.example.vviiblue.horoscapp.ui.palmistry.PalmistryFragment

import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HoroscopeFragment : Fragment() {
    /** Tip: Si tuviera una "clase Padre" de esta(osea esta estenderia de esa clase padre), que estienda de "Fragment",
     *  se puede configurar el binding en esa clase padre y no es necesario inicializar el binding en cada una de estas clases */

    /** La convención de nombres con un guión bajo inicial (_) indica que esta variable es mutable y puede cambiar su valor */
    /** ¡Se declara el "binding" en un fragment, calramente es diferente de declararlo en un activity */
    /** primero se declara un "_binding" que es nulleable
     *  luego si defino un "binding" que sobrescribe a la operacion get() y la igualo al "_binding"
     *  esto hace que cuando yo llame a "binding", lo que hare realmente es invocar a "_binding"*/
    /** se definde _binding con la barra baja porque son variables privadas que no se deben acceder, se le pone el "_" para identificarlas
     * entonces cuando se quiera accder al valor binding para usarlo, lo que va acceder es al "binding", que es un valor fijo pero
     * por detras se estara llamando a la variable "_binding", que es el que se puede modificar o romper */
    private var _binding: FragmentHoroscopeBinding? = null
    private val binding get() = _binding!! // se le agrega "!!" porque cuando yo llame a "binding" yo estoy muy seguro que "_binding" NO va a ser null, porque primero le estoy inflando un valor al "_binding" en "onCreateView"

    /** Para comunicar/enganchar el Fragment "HoroscopeFragment" con su ViewModel "HoroscopeViewModel" se  */
    /** se utiliza la función de extensión viewModels() para obtener una instancia de HoroscopeViewModel*/
    /** viewModels() ayuda con la creación y recuperación de instancias de ViewModel.*/
    private val horoscopeViewModel by viewModels<HoroscopeViewModel>() // by -> delega a ViewModelProvider  la creacion de una instancia de HoroscopeViewModel


    /** declaro adapter */
    private lateinit var horoscopeAdapter: HoroscopeAdapter

    /** Este metodo es el recomendado para cuando se trabaja con metodos configuracion,
     * cuando la vista ya se ha creado, es mejor usar este metodo */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    private fun initUI() {
        initUIState()
        initRecycleview()
    }

    /** para que el fragment pueda leer/suscribirse/engancharse al StateFlow con la lista de horoscopos que tiene el viewModel
     * y pueda quedar escuchando los cambios */
    private fun initUIState() {
        /** para realizar la suscripcion, necesito hacerlo dentro de la Corutine "lifecycleScope",
         * porque usar la corutine "lifecycleScope"?, porque justa esta se engancha al ciclo de vida del Fragment, es idonea para eso
         * de esta manera si el Fragmento va a morir, esta Corutine muere tambien, evitanso asi que se ejecuten metodos cuando el fragment este muriendo*/
        /** SIEMPRE QUE SE VAYA A USAR UNA CORUTINE EN UN FRAGMENT O UN ACTIVITY, usar ----"lifecycleScope"!!!!!----  */
        lifecycleScope.launch {
            //"repeatOnLifecycle" -> es decir.. cuando empiece el ciclo de vida
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                // agarro el HoroscopeViewModel y me engancho/suscribo(collect) al flow definido del el viewModel
                horoscopeViewModel.horoscope.collect() { listHoroscopos ->
                    /** Entonces... para reafirmar conocimiento */
                    /**  desde ahora SIEMPRE que modifique el "_horoscope.value" en el ViewModel
                     *  automaticamente va a llmarse lo que se defino aqui dentro */
                    horoscopeAdapter.updateList(listHoroscopos)

                }
            }

        }
    }

    private fun initRecycleview() {
        horoscopeAdapter =
            HoroscopeAdapter() { horoscopeSelected -> onItemSelected(horoscopeSelected) }

        /** binding.rvHoroscope.apply = agarra esta vista (rvHoroscope) y le vas aplicar estos atributos*/
        /** para evitar poner "binding.rvHoroscope.layoutManager" ,binding.rvHoroscope.adapter = ..... */
        binding.rvHoroscope.apply {
            //  layoutManager = LinearLayoutManager(requireContext()) /** requireContext(),context -> para obtener el context en un FRAGMENT!! */
            layoutManager = GridLayoutManager(
                requireContext(),
                2
            ) // Para que se muestra como grilla de 2 columnas de items el recycleview
            adapter = horoscopeAdapter
        }
    }

    private fun onItemSelected(horoscopeInfo: HoroscopeInfo) {

        /** segun el tipo de HoroscopeInfo que entra por parametro, se devuelve el Enum correspondiente */
        val tipoHoroscopoSeleccionado = when (horoscopeInfo){
            Aries -> HoroscopeModel.Aries
            Taurus -> HoroscopeModel.Taurus
            Gemini -> HoroscopeModel.Gemini
            Cancer -> HoroscopeModel.Cancer
            Leo -> HoroscopeModel.Leo
            Virgo -> HoroscopeModel.Virgo
            Libra -> HoroscopeModel.Libra
            Scorpio -> HoroscopeModel.Scorpio
            Sagittarius -> HoroscopeModel.Sagittarius
            Capricorn -> HoroscopeModel.Capricorn
            Aquarius -> HoroscopeModel.Aquarius
            Pisces -> HoroscopeModel.Pisces
        }

        /** entonces tipoHoroscopoSeleccionado contendra el modelo seleccionado representado como un Enumerado */
        // ahora haciendo uso del navigation.safeargs se puede pasar el enumerado guardado en "tipoHoroscopoSeleccionado" a "HoroscopeDetailActivity"
        // pero antes debe estar especificado en el main_graph que la pantalla "HoroscopeDetailActivity" va a recibir un argumento
        /** una vez definido en el main_graph que tipo de argumento espera la pantalla, la action "actionHoroscopeFragmentToHoroscopeDetailActivity"
         * se marcara en rojo exigiendo que se le pase ese argumento, por eso se le llama "safeargs" (argumento seguro) se encargara de obligarnos a pasar lo que se necestie*/


        /** para navegar a la pantalla de detelles..., ya habiendo creado la "action" que se genera al hacer el enlace
         * de la pantalla "HoroscopeFragment" con "HoroscopeDetailActivity" en el main_graph,
         * se hace uso de "androidx.navigation.safeargs" que es la forma mas segura, "androidx.navigation.safeargs" autogenera clases por detras con las configuraciones necesarias.*/
        /** entonces usando la operacion findNavController de "navController", se le pide que navegue a una pantalla (navigate) y
         * en este caso navigate pide que se le pase una direction, en este caso ya deberia existir "HoroscopeFragmentDirections"
         *  que es la clase generada al realizar la conexion a los detalles en main_graph, dicha clase "HoroscopeFragmentDirections" conoce
         *  la action que se autogenero con el "id" que la representa "actionHoroscopeFragmentToHoroscopeDetailActivity"*/
        findNavController().navigate(
            HoroscopeFragmentDirections.actionHoroscopeFragmentToHoroscopeDetailActivity(tipoHoroscopoSeleccionado)
        )
    }


    /** Este metodo se llama cuando se esta creando la vista del fragment */
    /** es util para el principio de la vista*/
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHoroscopeBinding.inflate(layoutInflater, container, false)
        /** lo que devuelvo es el "binding" */
        return binding.root
    }


}