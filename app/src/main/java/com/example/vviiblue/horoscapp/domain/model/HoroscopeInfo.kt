package com.example.vviiblue.horoscapp.domain.model

import com.example.vviiblue.horoscapp.R

/** lo hago con un sealed , porque se que la cantidad de objetos van a ser solo estos horoscopos
 * por ultimo al hacerlo asi, obligo que cuando se invoque este modelo se tengan que pasar la referencia de el nombre y de la imagen para devolver ese objeto
 * entonces en vez de tener que crear un objeto generico HoroscopeInfo y pasarle la imagen y titulo, solo llamo a Aries por ejemplo y se que tendra su imagen y su nombre referenciados de forma correcta
 * las selead class son utiles cuando es para algo que es limitado, que es fijo por ejemplo los colores del arco iris
 * */
sealed class HoroscopeInfo(val img:Int, val name:Int){
    data object Aries: HoroscopeInfo(R.drawable.aries, R.string.aries) /** tip*: data object es lo mismo que object, pero da la informacion mas clara al momento de depurar */
    data object Taurus: HoroscopeInfo(R.drawable.tauro, R.string.taurus)
    data object Gemini: HoroscopeInfo(R.drawable.geminis, R.string.gemini)
    data object Cancer: HoroscopeInfo(R.drawable.cancer, R.string.cancer)
    data object Leo: HoroscopeInfo(R.drawable.leo, R.string.leo)
    data object Virgo: HoroscopeInfo(R.drawable.virgo, R.string.virgo)
    data object Libra: HoroscopeInfo(R.drawable.libra, R.string.libra)
    data object Scorpio: HoroscopeInfo(R.drawable.escorpio, R.string.scorpio)
    data object Sagittarius: HoroscopeInfo(R.drawable.sagitario, R.string.sagittarius)
    data object Capricorn: HoroscopeInfo(R.drawable.capricornio, R.string.capricorn)
    data object Aquarius: HoroscopeInfo(R.drawable.aquario, R.string.aquarius)
    data object Pisces: HoroscopeInfo(R.drawable.piscis, R.string.pisces)
}