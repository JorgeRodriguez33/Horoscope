package com.example.vviiblue.horoscapp.ui.palmistry

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.PermissionChecker
import com.example.vviiblue.horoscapp.R
import com.example.vviiblue.horoscapp.databinding.FragmentPalmistryBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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

    /** Constante para guardar el permiso de la camara */
    companion object {
        private const val CAMERA_PERMISSION = android.Manifest.permission.CAMERA
    }

    /** para solicitar permiso en un fragment
     * se inicializa y se carga "requestPermissionLauncher" con el resultado de llamar a "registerForActivityResult()"*/
    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission() //ActivityResultContracts.RequestPermission() : aqui se solicita el permiso
    ) { isGranted -> // isGranted: indica si el permiso fue otorgado o no (true o false)
        if (isGranted) {
            startCamera()
        } else {
            Toast.makeText(
                requireContext(),
                "Acepta los permisos para poder disfrutar de una experiencia mágica",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (checkCameraPermission()) {
            //Tiene permisos aceptados
            startCamera()
        } else {
            /** Para pedir permisos en los Fragment se hace atravez de un luncher  */
            requestPermissionLauncher.launch(CAMERA_PERMISSION)
        }
    }

    private fun startCamera() {
        TODO("Not yet implemented")
    }

    /** Operacion para gestionar los permisos, si el usuario ya acepto por ejemplo el permiso de la camara, no se lo voy a volver a pedir */
    private fun checkCameraPermission(): Boolean {
        /** PermissionChecker: Comprobador de permiso */
        /** checkSelfPermission: Comprueba este permiso... */
        return PermissionChecker.checkSelfPermission(
            requireContext(),
            CAMERA_PERMISSION
        ) == PermissionChecker.PERMISSION_GRANTED
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPalmistryBinding.inflate(layoutInflater, container, false)

        /** lo que devuelvo es el "binding" */
        return binding.root
    }

}