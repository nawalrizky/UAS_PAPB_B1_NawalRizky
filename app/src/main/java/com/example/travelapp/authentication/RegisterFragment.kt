package com.example.travelapp.authentication

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.travelapp.databinding.FragmentRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import java.text.SimpleDateFormat
import java.util.*

class RegisterFragment : Fragment() {
    private val authViewModel: AuthViewModel by activityViewModels()
    private lateinit var auth: FirebaseAuth
    private val calendar = Calendar.getInstance()
    private val firestore = FirebaseFirestore.getInstance()
    private val roleCollectionRef = firestore.collection("data_role")
    private val dataUserCollectionRef = firestore.collection("data_user")

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = Firebase.auth

        binding.btnRegister.setOnClickListener {
            val username = binding.editTxtUsername.text.toString()
            val email = binding.editTxtEmail.text.toString()
            val password = binding.editTxtPassword.text.toString()
            val birthDate = binding.editTxtBirthdate.text.toString()

            if (validateInput(username, email, password, birthDate)) {
                // Register user with Firebase Authentication
                registerUser(username, email, password, birthDate)
            }
        }

        binding.editTxtBirthdate.setOnClickListener {
            showDatePickerDialog()
        }
    }

    private fun registerUser(username: String, email: String, password: String, birthDate: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    // Registration success
                    val user = auth.currentUser

                    // Add user role
                    addRole(RoleData(uid = user?.uid.toString(), username = username, role = "user"))

                    // Add user data
                    addDataUser(UserData(uid = user?.uid.toString(), username = username, email = email))

                    // Signal the navigation event to the AuthViewModel
                    authViewModel.navigateToLogin()
                } else {
                    // Registration failed
                    Toast.makeText(
                        requireContext(),
                        "Registrasi gagal. Periksa kembali informasi Anda.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

    private fun validateInput(
        username: String,
        email: String,
        password: String,
        birthDate: String
    ): Boolean {
        if (username.isEmpty() || email.isEmpty() || password.isEmpty() || birthDate.isEmpty()) {
            Toast.makeText(requireContext(), "Semua kolom harus diisi", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    private fun addRole(dataRole: RoleData) {
        roleCollectionRef.add(dataRole).addOnFailureListener {
            Log.d("Register", "Error adding role : ")
        }
    }

    private fun addDataUser(dataUser: UserData) {
        dataUserCollectionRef.add(dataUser).addOnFailureListener {
            Log.d("Register", "Error adding data user : ")
        }
    }

    private fun showDatePickerDialog() {
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            requireContext(),
            DatePickerDialog.OnDateSetListener { _, selectedYear, selectedMonth, selectedDay ->
                calendar.set(selectedYear, selectedMonth, selectedDay)
                val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                binding.editTxtBirthdate.setText(sdf.format(calendar.time))
            },
            year,
            month,
            day
        )
        datePickerDialog.datePicker.maxDate =
            System.currentTimeMillis() - 1000L * 60 * 60 * 24 * 365 * 15 // 15 years ago
        datePickerDialog.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
