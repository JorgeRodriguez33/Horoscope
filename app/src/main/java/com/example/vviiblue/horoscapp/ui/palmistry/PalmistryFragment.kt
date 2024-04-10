package com.example.vviiblue.horoscapp.ui.palmistry

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.vviiblue.horoscapp.R
import com.example.vviiblue.horoscapp.databinding.FragmentPalmistryBinding


class PalmistryFragment : Fragment() {

    /** ¡Se declara el "binding" en un fragment, calramente es diferente de declararlo en un activity */
    /** primero se declara un "_binding" que es nulleable
     *  luego si defino un "binding" que sobrescribe a la operacion get() y la igualo al "_binding"
     *  esto hace que cuando yo llame a "binding", lo que hare realmente es invocar a "_binding"*/
    /** se definde _binding con la barra baja porque son variables privadas que no se deben acceder, se le pone el "_" para identificarlas
     * entonces cuando se quiera accder al valor binding para usarlo, lo que va acceder es al "binding", que es un valor fijo pero
     * por detras se estara llamando a la variable "_binding", que es el que se puede modificar o romper */
    private var _binding: FragmentPalmistryBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPalmistryBinding.inflate(layoutInflater,container,false)

        /** lo que devuelvo es el "binding" */
        return binding.root
    }

}