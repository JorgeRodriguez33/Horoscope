package com.example.vviiblue.horoscapp.ui.luck

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.vviiblue.horoscapp.R
import com.example.vviiblue.horoscapp.databinding.FragmentLuckBinding

class LuckFragment : Fragment() {

    /** La convención de nombres con un guión bajo inicial (_) indica que esta variable es mutable y puede cambiar su valor */
    /** ¡Se declara el "binding" en un fragment, calramente es diferente de declararlo en un activity */
    /** primero se declara un "_binding" que es nulleable
     *  luego si defino un "binding" que sobrescribe a la operacion get() y la igualo al "_binding"
     *  esto hace que cuando yo llame a "binding", lo que hare realmente es invocar a "_binding"*/
    /** se definde _binding con la barra baja porque son variables privadas que no se deben acceder, se le pone el "_" para identificarlas
     * entonces cuando se quiera accder al valor binding para usarlo, lo que va acceder es al "binding", que es un valor fijo pero
     * por detras se estara llamando a la variable "_binding", que es el que se puede modificar o romper */
    private var _binding : FragmentLuckBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLuckBinding.inflate(layoutInflater,container,false)

        /** lo que devuelvo es el "binding" */
        return binding.root
    }


}