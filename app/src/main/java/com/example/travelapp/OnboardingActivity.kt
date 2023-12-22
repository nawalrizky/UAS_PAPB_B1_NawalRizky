package com.example.travelapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.example.travelapp.authentication.AuthActivity
import com.example.travelapp.admin.AdminActivity
import com.example.travelapp.databinding.ActivityOnboardingBinding

class OnBoardingActivity : AppCompatActivity() {

    private lateinit var btnCreateAccount: AppCompatButton
    private lateinit var binding: ActivityOnboardingBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("user_data", Context.MODE_PRIVATE)

        // Check the "Remember Me" status
        val rememberMe = sharedPreferences.getBoolean("rememberMe", false)
        if (rememberMe) {
            // If "Remember Me" is true, check the user role
            val userRole = sharedPreferences.getString("userRole", "")

            // Navigate based on user role
            when (userRole) {
                "admin" -> {
                    startActivity(Intent(this, AdminActivity::class.java))
                }
                else -> {
                    startActivity(Intent(this, MainActivity::class.java))
                }
            }
            finish()
        } else {
            // Otherwise, proceed with the regular onboarding flow
            binding = ActivityOnboardingBinding.inflate(layoutInflater)
            val view = binding.root
            setContentView(view)

            // Your existing code for button click
            btnCreateAccount = binding.btnToRegister
            btnCreateAccount.setOnClickListener {
                // Skip the login screen and go directly to AuthActivity
                startActivity(Intent(this, AuthActivity::class.java))
                finish()
            }
        }
    }
}
