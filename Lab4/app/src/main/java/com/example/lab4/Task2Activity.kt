package com.example.lab4

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class Task2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task2)

        val viewPager = findViewById<ViewPager2>(R.id.viewPager)
        val tabLayout = findViewById<TabLayout>(R.id.tabLayout)

        val adapter = Task2PagerAdapter(this)
        viewPager.adapter = adapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = "Фрагмент ${position + 1}"
        }.attach()
    }
}
