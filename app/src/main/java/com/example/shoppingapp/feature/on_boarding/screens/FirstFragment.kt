package com.example.shoppingapp.feature.on_boarding.screens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.shoppingapp.R
import com.example.shoppingapp.core.common.viewBinding
import com.example.shoppingapp.databinding.FragmentFirstBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FirstFragment: Fragment(R.layout.fragment_first) {

    private val binding by viewBinding(FragmentFirstBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewPager = activity?.findViewById<ViewPager2>(R.id.viewPager)
        binding.textViewNext.setOnClickListener {
            viewPager?.currentItem = 1
        }
    }
}