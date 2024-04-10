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

