package com.example.shoppingapp.feature.on_boarding.screens

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.shoppingapp.R
import com.example.shoppingapp.core.common.viewBinding
import com.example.shoppingapp.databinding.FragmentThirdBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ThirdFragment : Fragment(R.layout.fragment_third) {

    private val binding by viewBinding(FragmentThirdBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.textViewNext.setOnClickListener {
            findNavController().navigate(R.id.action_onBoardingFragment_to_navigation_home)
            onBoardingIsFinished()
        }
    }

    private fun onBoardingIsFinished() {
        val sharedPreferences =
            requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putBoolean("finished", true)
        editor.apply()
    }
}