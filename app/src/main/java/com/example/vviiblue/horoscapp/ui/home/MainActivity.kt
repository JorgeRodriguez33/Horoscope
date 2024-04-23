package com.example.vviiblue.horoscapp.ui.home

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.vviiblue.horoscapp.R
import com.example.vviiblue.horoscapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

/**         Test de ui al Main Activity
 * Por norma general, para testear ui, debe estar en los mismos directorios en el directorio "androidTest"
 * entonces para no estar recreando todos los directorios para hacer el test de "MainActivity"
 * lo mejor es posicionar el mouse sobre  la operacion "MainActivity" y precionar CTRL+SHIFT+T
 * por ultimo dar en crear test, crear*/

/** Para que esta classe reciba cosas inyectadas, basta poner el annotation @AndroidEntryPoint*/
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController /** NavController es la clase padre que gestiona el tema de la navegacion a travez de "NavigationComponent" */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUI()
    }

    private fun initUI() {
       initNavigation()
    }

    /** inicializo los recursos para la navegacion entre fragments */
    private fun initNavigation() {
       val navHost = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment // realizo el casteo a un NavHostFragment
        navController = navHost.navController // De esta manera el controlador definido va a controlar el "fragmentContainerView" definido en el xml del MainActivity
        binding.bottomNavView.setupWithNavController(navController) // le paso al bottomNavegation, el navigationController

    }


}

