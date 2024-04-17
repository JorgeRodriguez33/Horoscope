package com.example.vviiblue.horoscapp.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vviiblue.horoscapp.domain.model.HoroscopeModel
import com.example.vviiblue.horoscapp.domain.usecase.GetPredictionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**Inyecto el caso de uso "getPredictionUseCase" para poder solicitar a la capa de domain, la informacion necesaria */
@HiltViewModel
class HoroscopeDetailViewModel @Inject constructor(private val getPredictionUseCase: GetPredictionUseCase) : ViewModel() {
    /** Se crea el StateFlow, que sera enganchado por el "HoroscopeDetailActivity" */
    private var _state =
        MutableStateFlow<HoroscopeDetailState>(HoroscopeDetailState.Loading) // se le asigna un estado inicial
    val state: StateFlow<HoroscopeDetailState> = _state

    /** HoroscopeModel esta definido en domain, por la simplicidad del proyecto, se usa el model de la capa domain
     *  pero podria haber un model de horoscopo en la capa de ui y que se mappe por medio de una operacion,
     *  como lo hace "toDomain" para mappear de "PredictionResponse" definido en capa data a
     *  "HoroscopeModel" definida en domain y a su vez se podria pasar a "HoroscopeModel" definida en la capa ui con una operacion "toUi" por ejemplo....
     * */
    lateinit var horoscope: HoroscopeModel // este tipos de informacion que queremos mantener como un auxiliar, va simpre en el viewmodel

    fun getHoroscope(sign: HoroscopeModel) {
        horoscope = sign
        /** lanzo una corrutina, para invocar la operacion del caso de uso que es Suspend */
        viewModelScope.launch {

            //cosas haciendose en el HILO principal
            _state.value = HoroscopeDetailState.Loading

            /** Como quiero que SOLO la logica del caso de uso lo haga en un hilo secundario, */
            val result = withContext(Dispatchers.IO) { getPredictionUseCase(sign.name) } //cosas haciendose en el HILO secundario

            //cosas haciendose en el HILO principal
            if(result!=null){
                _state.value = HoroscopeDetailState.Success(result.horoscope, result.sign, horoscope) // devuelvo un "HoroscopeDetailState" de tipo Success cargado con "(result.horoscope, result.sign, horoscope)"
            }else{
                _state.value = HoroscopeDetailState.Error("Ha ocurrido un error, intentelo mas tarde")
            }

        }
    }

}