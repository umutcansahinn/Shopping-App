package com.example.shoppingapp.feature.on_boarding.adapter


import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.shoppingapp.feature.on_boarding.screens.FirstFragment
import com.example.shoppingapp.feature.on_boarding.screens.SecondFragment
import com.example.shoppingapp.feature.on_boarding.screens.ThirdFragment

class ViewPagerAdapter(
    list: ArrayList<Fragment>,
    fm: FragmentManager,
    lifecycle: Lifecycle
): FragmentStateAdapter(fm, lifecycle) {

    private val fragmentList = list

    override fun getItemCount(): Int = fragmentList.size

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]

    }
}