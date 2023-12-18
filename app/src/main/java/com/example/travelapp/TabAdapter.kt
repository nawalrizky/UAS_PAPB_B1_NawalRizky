package com.example.travelapp

import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class TabAdapter(fm: FragmentManager, lifecycle: Lifecycle): FragmentStateAdapter(fm,lifecycle) {

        val page = arrayOf(BookFragment(),ScheduleFragment())

        override fun getItemCount(): Int {
            return page.size
        }

        override fun createFragment(position: Int): androidx.fragment.app.Fragment {
            return page[position]
        }
}