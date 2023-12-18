package com.example.travelapp


import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class AuthTabAdapter(fm: FragmentManager, lifecycle: Lifecycle) : FragmentStateAdapter(fm, lifecycle) {

    private val pages = arrayOf(RegisterFragment(), LoginFragment())

    override fun getItemCount(): Int {
        return pages.size
    }

    override fun createFragment(position: Int): androidx.fragment.app.Fragment {
        return pages[position]
    }
}
