package com.example.vviiblue.horoscapp.ui.horoscope

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.vviiblue.horoscapp.databinding.FragmentHoroscopeBinding

class HoroscopeFragment : Fragment() {
    /** Tip: Si tuviera una "clase Padre" de esta(osea esta estenderia de esa clase padre), que estienda de "Fragment",
     *  se puede configurar el binding en esa clase padre y no es necesario inicializar el binding en cada una de estas clases */


    /** ¡Se declara el "binding" en un fragment, calramente es diferente de declararlo en un activity */
    /** primero se declara un "_binding" que es nulleable
     *  luego si defino un "binding" que sobrescribe a la operacion get() y la igualo al "_binding"
     *  esto hace que cuando yo llame a "binding", lo que hare realmente es invocar a "_binding"*/
    /** se definde _binding con la barra baja porque son variables privadas que no se deben acceder, se le pone el "_" para identificarlas
     * entonces cuando se quiera accder al valor binding para usarlo, lo que va acceder es al "binding", que es un valor fijo pero
     * por detras se estara llamando a la variable "_binding", que es el que se puede modificar o romper */
    private var _binding: FragmentHoroscopeBinding? = null
    private val binding get() = _binding!! // se le agrega "!!" porque cuando yo llame a "binding" yo estoy muy seguro que "_binding" NO va a ser null, porque primero le estoy inflando un valor al "_binding" en "onCreateView"

    /** Este metodo se llama cuando se crea la vista del fragment */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHoroscopeBinding.inflate(layoutInflater, container, false)

        /** lo que devuelvo es el "binding" */
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    private fun initUI() {

    }
}