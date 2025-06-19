package com.example.lab4

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class Task2PagerAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {


    override fun getItemCount(): Int = 4


    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> FragmentOne()
            1 -> FragmentTwo()
            2 -> FragmentThree()
            3 -> FragmentFour()
            else -> FragmentOne()
        }
    }
}
