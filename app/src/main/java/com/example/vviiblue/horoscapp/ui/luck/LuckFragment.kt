package com.example.vviiblue.horoscapp.ui.luck

import android.animation.ObjectAnimator
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.Animation.AnimationListener
import android.view.animation.AnimationUtils
import android.view.animation.DecelerateInterpolator
import androidx.core.animation.doOnEnd
import androidx.core.view.isVisible
import com.example.vviiblue.horoscapp.R
import com.example.vviiblue.horoscapp.databinding.FragmentLuckBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.Random

@AndroidEntryPoint
class LuckFragment : Fragment() {

    private var _binding : FragmentLuckBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUI()
    }

    private fun initUI() {
       initListeners()
    }

    private fun initListeners() {
       binding.ivRoulette.setOnClickListener { spinRoulette() }
    }

    private fun spinRoulette() { //metodo para que la ruleta gire, nada mas

        val random = Random()
        val degrees = random.nextInt(1440) + 360 // cuantos grados va a girar, se agrega random para que la cantidad de grados no sea igual siempre

        // creo un objeto animator
        val animator = ObjectAnimator
                                     .ofFloat(
                                            binding.ivRoulette, //vista a la que quiero aplicar una animacion
                                            View.ROTATION, //Se pueden hacer "muchos" cambios para aplicar y hacer la animacion, elijo ROTATION gira sobre el punto medio de la vista (la imagen) sino se puede poner para rotar sobre los ejes
                                            0f, //va a rotar desde 0 grados
                                            degrees.toFloat() // va a rotar hasta degrees.toFloat() grados
                                     )

        /** la animacion va a durar 2 segundos */
        animator.duration = 2000
        /** le indico el interpolator de la aniamcion si es lineal va siempre a la misma velocidad la animacion y si es "DecelerateInterpolator" empieza rapida y luego se va enlenteciendo (asi funcionan las ruletas...)*/
        animator.interpolator = DecelerateInterpolator()

        /** Le indico que "cuando termine la animacion", va a llamar a la funcion lambda slideCard() */
        animator.doOnEnd { slideCard() }

        /** ejecutar la animacion */
        animator.start()

    }

    // Otra forma de crear animacion en android...
    private fun slideCard() {
        /** Esta funcion va a crear una animacion para mostrar que se desliza una carta hacia arriba,
         * y si, esta animacion se ejecuta al final de la anterior, es un enganche de animaciones */

        //Usando la herramienta "AnimationUtils" de animaciones de android,
        // para cargar animaciones creadas en xml
        /** Creo un directorio llamado anim y dentro creo la animacion  "slide_up" */
        /** en la animacion creada se define la traslacion de la vista "binding.ivReverse"*/
        val slideUpAnimation = AnimationUtils
                                         .loadAnimation( //cargo una animacion existente
                                             requireContext(), // solicito un contexto
                                             R.anim.slide_up //paso la animacion que quiero cargar del .xml
                                         )

        /** para conrolar los estados de la animacion  */
        /** cuando se usa "AnimationUtils" es por medio de   */
        /** para conrolar los estados de la animacion setAnimationListener */
        /** Si quiero que haga algo "cuando empiece la animacion" impelemnto "onAnimationStart(p0: Animation?)"  */
        /** Si quiero que haga algo "cuando termine la animacion" impelemnto "onAnimationEnd(p0: Animation?)"  */
        /** Si quiero que haga algo "cuando se vata a REPETIR la animacion" impelemnto "onAnimationRepeat(p0: Animation?)"  */
        slideUpAnimation.setAnimationListener(object : Animation.AnimationListener{
            override fun onAnimationStart(p0: Animation?) {
                // Cuando la animacion vaya a empezar, hace visible la vista (el Imagen View)
                binding.ivReverse.isVisible = true
            }

            override fun onAnimationEnd(p0: Animation?) {
                //cuando la animacion finalice, invoco otra operacion para ejecutar la animacion que haga crecer la vista (ImagenView)
                growCard()
            }

            /** Se puede configurar para que se ejecute n veces */
            override fun onAnimationRepeat(p0: Animation?) {
               // En este caso no quiero que haga nada
            }
        })
        /** IMPORTANTE, para cargar una animacion la vista tiene que haber precargado el render, osea tiene que estar en la pantalla
         *  si no se quiere ver tiene que estar como Invisible, jamas como "gone",
         *  en "gone" no esta cargado aun en el render de la app" */
        //por ulitmo para ejecutar la animacion, tomo la vista, le pido que inicie una animacion "startAnimation" y
        // le paso la animacion que debe iniciar
        binding.ivReverse.startAnimation(slideUpAnimation)

    }

    private fun growCard() {
        val growAnimation = AnimationUtils.loadAnimation(requireContext(), R.anim.grow)

        growAnimation.setAnimationListener(object : Animation.AnimationListener{
            override fun onAnimationStart(p0: Animation?) {

            }

            override fun onAnimationEnd(p0: Animation?) {
                // Cuando la animacion vaya a terminar, oculta la vista (el Imagen View)
                binding.ivReverse.isVisible = false

                //ejecuto funcion al finalizar la animacion, para mostrar la nueva vista, con una animacion que la muestre
                showPremonitionView()
            }

            override fun onAnimationRepeat(p0: Animation?) {

            }

        })

        binding.ivReverse.startAnimation(growAnimation)
    }

    private fun showPremonitionView() {
        /** Otro tipo de animacion, ademas de la manual con AnimationObject o animationUtil */
        /** animacion creada con "AlphaAnimation", AlphaAnimation lo que hace es cambiar la opacidad
         * en este caso 1.0f es el 100% visible y lo pasa a 0.0f que seria 0% visible */
        val disappearAnimation = AlphaAnimation(1.0f,0.0f)
        disappearAnimation.duration = 200  //la animacion va a pasar en 200 milisegundos

        /** animacion creada con "AlphaAnimation",
         * en este caso 0.0f es el 0% visible y lo pasa a 1.0f que seria 100% visible, esto
         * para que la imagen de la vista de la prediccion se muestre de a poco y mejore la experiencia del usuario*/
        val appearAnimation = AlphaAnimation(0.0f,1.0f)
        appearAnimation.duration = 1000


        // ahora le aplico listener al "AlphaAnimation"
        disappearAnimation.setAnimationListener(object : AnimationListener {
            override fun onAnimationStart(p0: Animation?) {

            }

            override fun onAnimationEnd(p0: Animation?) {
                /** Cuando termine esta aniamacion, voy a mandar a "Gone" la vista con que tiene la ruleta y
                 * a la vista que muestra la prediccion y el boton de compartir lo hago "Visible" */
                binding.viewPreview.isVisible = false
                binding.viewPrediction.isVisible = true
            }

            override fun onAnimationRepeat(p0: Animation?) {

            }
        })

        binding.viewPreview.startAnimation(disappearAnimation)
        binding.viewPrediction.startAnimation(appearAnimation)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLuckBinding.inflate(layoutInflater,container,false)

        return binding.root
    }


}