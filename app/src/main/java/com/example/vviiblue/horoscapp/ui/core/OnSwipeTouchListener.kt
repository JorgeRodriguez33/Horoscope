package com.example.vviiblue.horoscapp.ui.core

import android.content.Context
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View


/** como esto es algo "generico" para toda la aplicacion pero dentro de la capa ui
 * genero un directorio core en "ui"
 * la clase necesita "Context" y extiende de la clase "OnTouchListener" que pertenece a las vistas
 * recordar que esta operacion sera una "personalizacion" de OnTouchListener para simular el "Swipe" para la ruleta*/
open class OnSwipeTouchListener(context: Context) : View.OnTouchListener {

    companion object {
        private const val SWIPE_THRESHOLD = 100
        private const val SWIPE_VELOCITY_THRESHOLD = 100
    }

    /** GestureDetector: es una libreria de Android, que ayuda a detectar gestos */
    private val gestureDetector: GestureDetector

    // init es un contructor, es lo primero que se llama
    init {
        /** "Al detector de gestos" hay que pasarle un "Listener" (GestureListener())
         * El "Listener" es el que crea lo que estamos haciendo con la mano */
        gestureDetector = GestureDetector(context, GestureListener())
    }

    override fun onTouch(v: View, event: MotionEvent): Boolean {
        return gestureDetector.onTouchEvent(event)
    }

    private inner class GestureListener : GestureDetector.SimpleOnGestureListener() {


        override fun onDown(e: MotionEvent): Boolean {
            return true
        }

        override fun onFling(
            e1: MotionEvent?,
            e2: MotionEvent,
            velocityX: Float,
            velocityY: Float
        ): Boolean {
            var result = false
            try {
                val diffY = e2.y - e1!!.y
                val diffX = e2.x - e1.x
                if (Math.abs(diffX) > Math.abs(diffY)) {
                    if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                        if (diffX > 0) {
                            onSwipeRight()
                        } else {
                            onSwipeLeft()
                        }
                        result = true
                    }
                } else if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                    if (diffY > 0) {
                        onSwipeBottom()
                    } else {
                        onSwipeTop()
                    }
                    result = true
                }
            } catch (exception: Exception) {
                exception.printStackTrace()
            }

            return result
        }


    }

    open fun onSwipeRight() {}

    open fun onSwipeLeft() {}

    open fun onSwipeTop() {}

    open fun onSwipeBottom() {}
}