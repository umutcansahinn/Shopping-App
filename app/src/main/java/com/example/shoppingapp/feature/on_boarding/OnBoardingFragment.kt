package com.example.shoppingapp.feature.on_boarding

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.shoppingapp.R
import com.example.shoppingapp.core.common.viewBinding
import com.example.shoppingapp.databinding.FragmentOnBoardingBinding
import com.example.shoppingapp.feature.on_boarding.adapter.ViewPagerAdapter
import com.example.shoppingapp.feature.on_boarding.screens.FirstFragment
import com.example.shoppingapp.feature.on_boarding.screens.SecondFragment
import com.example.shoppingapp.feature.on_boarding.screens.ThirdFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnBoardingFragment : Fragment(R.layout.fragment_on_boarding) {

    private val binding by viewBinding(FragmentOnBoardingBinding::bind)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fragmentList = arrayListOf(
            FirstFragment(),
            SecondFragment(),
            ThirdFragment()
        )

        val adapter = ViewPagerAdapter(
            fragmentList,
            requireActivity().supportFragmentManager,
            lifecycle
        )
        binding.viewPager.adapter = adapter
        binding.dotsIndicator.attachTo(binding.viewPager)
    }
}