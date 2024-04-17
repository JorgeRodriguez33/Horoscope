package com.example.vviiblue.horoscapp.ui.detail

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.navArgs
import com.example.vviiblue.horoscapp.R
import com.example.vviiblue.horoscapp.databinding.ActivityHoroscopeDetailBinding
import com.example.vviiblue.horoscapp.domain.model.HoroscopeModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

/** Activity a la que se llegara cuando se de click sobre uno de los hosocopos del HoroscopeFragment */
@AndroidEntryPoint
class HoroscopeDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHoroscopeDetailBinding


    /** Las dos formas son validas para inicializarel ViewModel, en uno se debe pasar el tipo al viewsModel porque no esta definido el tipo del valor*/
    // private val horoscopeDetailViewModel by viewModels<HoroscopeDetailViewModel>()
    private val horoscopeDetailViewModel: HoroscopeDetailViewModel by viewModels()

    /** --------------------------------------------------- */


    /** Recuperando el valor envidado desde "HoroscopeFragment" , las dos formas sirven*/
    // private val args by navArgs<HoroscopeDetailActivityArgs>()
    private val args: HoroscopeDetailActivityArgs by navArgs() // Ahora ya se sabe que tipo de horoscopo hay que consultar con Retrofi

    /** Para esta pantalla se trabajara con FLow pero con Estados (pueden ser Error,Exito,Cargando ), para hacer esto, se crea una clase que gestione estos estados */
    /** Entonces el mutable StateFlow sera de tipo "HoroscopeDetailState", entonces el viewModel seteara los estados en el Flow Loading, Error o Success ....
     *  */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /** args.tipoHoroscopoSeleccionado  me devuelve el valor que pase por argumento, en este caso es el Enum que pase*/
        binding = ActivityHoroscopeDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUI()
        horoscopeDetailViewModel.getHoroscope(args.tipoHoroscopoSeleccionado)
    }

    private fun initUI() {

        initListeners()
        initUIState()
    }

    private fun initListeners() {
        //para el boton de retornar
        binding.ivBack.setOnClickListener { onBackPressed() }
    }

    private fun initUIState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                horoscopeDetailViewModel.state.collect { estado ->
                    when (estado) { //Dependiendo del estado que se pone en el Flow, se realizara una accion en la ui
                        HoroscopeDetailState.Loading -> loadingState()

                        is HoroscopeDetailState.Error -> errorState()

                        is HoroscopeDetailState.Success -> successState(estado)

                    }

                }
            }
        }
    }

    private fun loadingState() {
        binding.pb.isVisible = true
    }

    private fun errorState() {
        binding.pb.isVisible = false
    }

    private fun successState(estado: HoroscopeDetailState.Success) {
        binding.pb.isVisible = false
        binding.tvTitle.text = estado.sign
        binding.tvBody.text = estado.prediccion

        val referenciaImgHoroscopo = when (estado.horoscopeModel) {
            HoroscopeModel.Aries -> R.drawable.detail_aries
            HoroscopeModel.Taurus -> R.drawable.detail_taurus
            HoroscopeModel.Gemini -> R.drawable.detail_gemini
            HoroscopeModel.Cancer -> R.drawable.detail_cancer
            HoroscopeModel.Leo -> R.drawable.detail_leo
            HoroscopeModel.Virgo -> R.drawable.detail_virgo
            HoroscopeModel.Libra -> R.drawable.detail_libra
            HoroscopeModel.Scorpio -> R.drawable.detail_scorpio
            HoroscopeModel.Sagittarius -> R.drawable.detail_sagittarius
            HoroscopeModel.Capricorn -> R.drawable.detail_capricorn
            HoroscopeModel.Aquarius -> R.drawable.detail_aquarius
            HoroscopeModel.Pisces -> R.drawable.detail_pisces
        }

        binding.ivDetail.setImageResource(referenciaImgHoroscopo)
    }

}
