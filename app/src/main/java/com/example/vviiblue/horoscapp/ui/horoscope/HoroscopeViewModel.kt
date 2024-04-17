package com.example.vviiblue.horoscapp.ui.horoscope

import androidx.lifecycle.ViewModel
import com.example.vviiblue.horoscapp.data.providers.HoroscopeProvider
import com.example.vviiblue.horoscapp.domain.model.HoroscopeInfo
import com.example.vviiblue.horoscapp.domain.model.HoroscopeInfo.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

/** agregando la annotation @HiltViewModel, este viewModel ya queda preparado para conectar con DaggerHilr */
/** para meterle informacion/ inyectarle informacion , se agrega "@Inject constructor()",
 * dentro de los parentecis del constructor() se puede inyetar la clase que se necesita en el HoroscopeViewModel, esto se encarga DaggerHilt */
@HiltViewModel
class HoroscopeViewModel @Inject constructor(private val horoscopeProvider: HoroscopeProvider) : ViewModel() {
/** el HoroscopeViewModel sera el encargadode recuperar la informacion (HoroscopeInfo)
 *  lo hara por estados (patron mvvm), para saber cuando debe obtener la informacion, siempre estara escuchando, para esto se usa StateFlow
 *  los FLow sera una comunicaacion constante siempre
 *  por lo tanta el fragment "HoroscopeFragment" se puede enganchar a un FLow que se defina aqui */
 /** La idea es que el HoroscopeFragment se enganche al Flow que esta declarado en HoroscopeViewModel */
    // **********************************************
 /** declaro el flow, dentro del estado de Flow le pongo la lista de HoroscopeInfo y la inicializo vacia */
 /** _horoscope es una variable mutable(que se puede modificar), pero la idea es que las modificaciones de esta variable no
  *  se generen de afuera, entonces se crea un valor que No es mutable que sea al que se pueda acceder desde afuera, y evito que desde
  *  afuera realicen modificaciones en la lista*/
 private  var _horoscope = MutableStateFlow<List<HoroscopeInfo>>(emptyList())
 val horoscope:StateFlow<List<HoroscopeInfo>> = _horoscope // este valor sera el accesible desde el HoroscopeFragment...

  /** viewmodel cuenta con un metodo especial, llamado "init", sera lo primero que se ejecutara es el "OnCreate" de las Activity */
 init {
  /** obtengo la lista de horoscopos de HoroscopeProvider, por medio de Dagger Hilt y se lo asigno al Flow */
    val listHoroscopes:List<HoroscopeInfo> = horoscopeProvider.getHoroscopes()
    _horoscope.value = listHoroscopes
 }


}