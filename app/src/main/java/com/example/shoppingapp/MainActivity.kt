package com.example.shoppingapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.shoppingapp.core.common.gone
import com.example.shoppingapp.core.common.viewBinding
import com.example.shoppingapp.core.common.visible
import com.example.shoppingapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by  viewBinding(ActivityMainBinding::inflate)
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment

        navController = navHostFragment.navController

        NavigationUI.setupWithNavController(binding.navView,navController)

        navHostFragment.navController.addOnDestinationChangedListener { _,destination,_->

            when(destination.id) {
                R.id.homeDetailFragment,
                R.id.splashFragment,
                R.id.onBoardingFragment -> binding.navView.gone()

                else -> binding.navView.visible()
            }
        }
        supportActionBar?.hide()

    }
}
