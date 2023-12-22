package com.example.travelapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.travelapp.authentication.AuthActivity
import com.example.travelapp.databinding.FragmentProfileBinding
import com.google.firebase.auth.FirebaseAuth

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Obtain SharedPreferences instance from the activity
        val sharedPreferences =
            requireActivity().getSharedPreferences("user_data", Context.MODE_PRIVATE)

        // Retrieve email and username from SharedPreferences
        val userEmail = sharedPreferences.getString("userEmail", "") ?: ""
        val username = sharedPreferences.getString("username", "") ?: ""

        // Update UI with retrieved values
        with(binding) {
            txtUsername.text = username

            btnLogout.setOnClickListener {
                // Clear SharedPreferences
                val sharedPreferences =
                    requireActivity().getSharedPreferences("user_data", Context.MODE_PRIVATE)
                val editor = sharedPreferences.edit()
                editor.clear()
                editor.apply()

                // Sign out from Firebase
                FirebaseAuth.getInstance().signOut()

                // Navigate to AuthActivity
                val intent = Intent(requireContext(), AuthActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }
        }

    }
}
