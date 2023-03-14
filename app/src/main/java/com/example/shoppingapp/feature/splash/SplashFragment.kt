package com.example.shoppingapp.feature.splash

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.shoppingapp.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashFragment : Fragment(R.layout.fragment_splash) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            delay(4000)
            if (onBoardingIsFinished()) {
                findNavController().navigate(R.id.action_splashFragment_to_navigation_home)
            } else {
                findNavController().navigate(R.id.action_navigation_splash_to_navigation_on_boarding)
            }
        }
    }

    private fun onBoardingIsFinished(): Boolean {
        val sharedPreferences =
            requireActivity().getSharedPreferences(getString(R.string.on_boarding), Context.MODE_PRIVATE)
        return sharedPreferences.getBoolean(getString(R.string.finished), false)
    }
}