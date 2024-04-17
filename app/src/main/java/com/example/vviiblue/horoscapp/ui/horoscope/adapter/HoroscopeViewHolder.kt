package com.example.vviiblue.horoscapp.ui.horoscope.adapter

import android.view.View
import android.view.animation.LinearInterpolator
import androidx.recyclerview.widget.RecyclerView
import com.example.vviiblue.horoscapp.databinding.ItemHoroscopeBinding
import com.example.vviiblue.horoscapp.domain.model.HoroscopeInfo

class HoroscopeViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemHoroscopeBinding.bind(view)
    private val context =
        binding.tvHoroscope.context // cualquiera de las view/vistas tienen "contexto"

    fun render(horoscopeInfo: HoroscopeInfo, onItemSelected: (HoroscopeInfo) -> Unit) {
        binding.tvHoroscope.text = context.getString(horoscopeInfo.name)
        binding.ivHoroscope.setImageResource(horoscopeInfo.img)
        binding.parent.setOnClickListener { horoscopeSelected ->
            /** antes de invocar a la operacion delegada(implementada en el HoroscopeFragment)
             *  voy a invocar una animacion, paso a la operacion la vista que se vera afectada por la animacion y
             *  una operacion lambda que "tendra dentro" la "opracion lambda onItemSelected"*/
            startRotationAnimation(
                binding.ivHoroscope,
                newLambdaToExecItemSelected = { onItemSelected(horoscopeInfo) })
            // onItemSelected(horoscopeInfo)
        }
    }

    /** recibe una "View"  por parametro que la operacion sea generica y acepte cualquier tipo de vista*/
    fun startRotationAnimation(view: View, newLambdaToExecItemSelected: () -> Unit) {
        /** agarro la animacion, le pido que se "anime" (animate()) , pero antes le aplico algunas propiedades "apply{}" */
        view.animate().apply {
            duration = 250// cuanto va a durar la animacion en milisegundos
            interpolator =
                LinearInterpolator() //El interpolator define el flujo/camino de la animacion, en este caso lineal "LinearInterpolator", la animacion va a tener la misma velocidad desde el principio hasta el final // Si se pusiera "acelerada", comenzarada lenta la animacion y luego terminaria mas rapido
            // rotationBy(360f) //que el view gire 360° sobre su propio punto del medio, seria como una rueda
            rotationYBy(180f) //que el view gire 180° sobre su eje y

            /** Yo necesito que se ejecute la animacion entera, recien luego de que termina la animacion, ejecuto "onItemSelected(horoscopeInfo)"
             * entonces para eso hago uso de "withEndAction{}", el codigo dentro de las llaves "{}" es otra funcion lambda, es por ello que
             * le paso por paramtro la nueva operacion lambda (newLambdaToExecItemSelected que tiene dentro la operacion labda orginal) para que se ejecute dentro de las llaves
             * asi es como se logra 1°) Animacion completa -> 2°) luego ejecucion de otra cosa*/
            withEndAction { newLambdaToExecItemSelected() }
            start()// que se ejecute la animacion
        }
    }


}