package com.example.travelapp

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.travelapp.databinding.FragmentHomeBinding
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    lateinit var mediator: TabLayoutMediator
    lateinit var viewPager2: ViewPager2

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Obtain SharedPreferences instance from the activity
        val sharedPreferences = requireActivity().getSharedPreferences("user_data", Context.MODE_PRIVATE)

        val username = sharedPreferences.getString("username", "") ?: ""
        binding.textUsername.text = "Halo, $username"

        with(binding) {
            viewPager2 = viewPager
            viewPager2.adapter = TabAdapter(childFragmentManager, lifecycle)
            mediator = TabLayoutMediator(tabLayout, viewPager2)
            { tab, position ->
                when (position) {
                    0 -> tab.text = "Book"
                    1 -> tab.text = "Schedule"
                }
            }
            mediator.attach()
        }
    }
}
