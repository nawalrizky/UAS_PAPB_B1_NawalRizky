package com.example.travelapp.authentication

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.travelapp.MainActivity
import com.example.travelapp.admin.AdminActivity
import com.example.travelapp.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private val authViewModel: AuthViewModel by viewModels()
    private lateinit var auth: FirebaseAuth

    private lateinit var sharedPreferences: SharedPreferences


    private val firestore = FirebaseFirestore.getInstance()
    private val userCollectionRef = firestore.collection("data_user")
    private val roleCollectionRef = firestore.collection("data_role")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        auth = FirebaseAuth.getInstance()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedPreferences =
            requireActivity().getSharedPreferences("user_data", Context.MODE_PRIVATE)

        with(binding) {
            // Check the state of the Remember Me checkbox
            checkBoxRememberMe.isChecked = sharedPreferences.getBoolean("rememberMe", false)

            btnLogin.setOnClickListener {
                val email = editTxtEmail.text.toString()
                val password = editTxtPassword.text.toString()
                val rememberMe = checkBoxRememberMe.isChecked

                // Perform Firebase Authentication
                loginUser(email, password, rememberMe)
            }

            // Add logic to show/hide the password (optional) here.
        }

        // Observe the navigation event
        authViewModel.navigateToLogin.observe(viewLifecycleOwner) { shouldNavigate ->
            if (shouldNavigate) {
                navigateToMainPage()
                authViewModel.navigationHandled()
            }
        }
    }

    private fun loginUser(email: String, password: String, rememberMe: Boolean) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    // Save the state of the Remember Me checkbox to SharedPreferences
                    with(sharedPreferences.edit()) {
                        putBoolean("rememberMe", rememberMe)
                        apply()
                    }

                    // Pemanggilan fungsi checkRole setelah login berhasil
                    checkRole(auth.currentUser?.uid ?: "")
                } else {
                    // Display an error message if login fails
                    Toast.makeText(
                        requireContext(),
                        "Login failed. Check your credentials.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

    private fun checkRole(uid: String) {
        roleCollectionRef.whereEqualTo("uid", uid)
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    if (!task.result?.isEmpty!!) {
                        for (document in task.result.documents) {
                            when (document.getString("role")) {
                                "user" -> {
                                    Log.d(ContentValues.TAG, "user")
                                    saveUserRole("user", document.getString("username") ?: "")
                                    navigateToMainPage()
                                }
                                "admin" -> {
                                    Log.d(ContentValues.TAG, "admin")
                                    saveUserRole("admin", document.getString("username") ?: "")
                                    navigateToAdminPage()
                                }
                                else -> {
                                    // Handle other roles if needed
                                }
                            }
                        }
                    } else {
                        Log.d(ContentValues.TAG, "User not found in the role collection")
                    }
                } else {
                    Log.e(ContentValues.TAG, "Error getting documents: ", task.exception)
                }
            }
    }





    private fun saveUserRole(role: String, username: String) {
        with(sharedPreferences.edit()) {
            putString("userRole", role)
            putString("username", username)
            apply()
        }
    }

    private fun navigateToMainPage() {
        if (isAdded) { // Check if the fragment is attached
            val intent = Intent(requireContext(), MainActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        }
    }

    private fun navigateToAdminPage() {
        if (isAdded) { // Check if the fragment is attached
            val intent = Intent(requireContext(), AdminActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
