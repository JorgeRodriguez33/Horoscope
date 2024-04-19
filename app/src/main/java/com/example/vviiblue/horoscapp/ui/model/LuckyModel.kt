package com.example.vviiblue.horoscapp.ui.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

/** El modelo en ui que representara el modelo para mostrar la imagen y el texto de la prediccion */
/** como necetio un modelo de datos que pese lo menos posible, por eso solammente se pasa
 * @DrawableRes val image:Int,
 * @StringRes val text:Int
 *sin necesidad de tener reerencias, porque no voy a necesitar tener todo a la vez
 *  @DrawableRes y @StringRes  son anotaciones de "Android" por eso el modelo debe estar en la capa "ui"
 *  @DrawableRes nos obliga a que el valor que se le pase, sea una referencia de un Drawable
 *  @StringRes nos obliga a que el valor que se le pase, sea una referencia de un String
 *  Esto es una muy buena practica*/
data class LuckyModel(
    @DrawableRes val image:Int,
    @StringRes val text:Int
)