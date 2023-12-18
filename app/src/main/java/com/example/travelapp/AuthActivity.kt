package com.example.travelapp
import AuthViewModel
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.example.travelapp.databinding.ActivityAuthBinding
import com.google.android.material.tabs.TabLayoutMediator

class AuthActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAuthBinding
    private lateinit var viewPager2: ViewPager2

    private val authViewModel: AuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            viewPager2 = viewPager
            viewPager2.adapter = AuthTabAdapter(supportFragmentManager, lifecycle)

            // Correct TabLayoutMediator setup
            TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
                when (position) {
                    0 -> tab.text = "Register"
                    1 -> tab.text = "Login"
                }
            }.attach()
        }

        // Observe the navigation event
        authViewModel.navigateToLogin.observe(this) { shouldNavigate ->
            if (shouldNavigate) {
                // Use supportFragmentManager to replace fragments within the ViewPager2
                viewPager2.currentItem = 1 // Assuming "Login" is at position 1
                authViewModel.navigationHandled()
            }
        }

    }
}
