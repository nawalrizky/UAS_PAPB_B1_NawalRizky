package com.example.travelapp

import AuthViewModel
import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.travelapp.databinding.FragmentRegisterBinding
import java.text.SimpleDateFormat
import java.util.*

class RegisterFragment : Fragment() {
    private val authViewModel: AuthViewModel by activityViewModels()
    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    private val calendar = Calendar.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnRegister.setOnClickListener {
            val username = binding.editTxtUsername.text.toString()
            val email = binding.editTxtEmail.text.toString()
            val password = binding.editTxtPassword.text.toString()
            val birthDate = binding.editTxtBirthdate.text.toString()

            if (validateInput(username, email, password, birthDate)) {
                val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                val birthDateDate = sdf.parse(birthDate)
                val currentDate = Date()
                val ageCalendar = Calendar.getInstance()
                ageCalendar.time = birthDateDate
                ageCalendar.add(Calendar.YEAR, 15)

                if (currentDate.after(ageCalendar.time)) {
                    // Usia memenuhi syarat
                    // Simpan data pengguna ke SharedPreferences
                    saveUserData(username, email, password, birthDate)

                    // Signal the navigation event to the AuthViewModel
                    authViewModel.navigateToLogin()

                    // No need to manually navigate here
                } else {
                    // Tampilkan pesan kesalahan jika usia tidak memenuhi syarat
                    Toast.makeText(
                        requireContext(),
                        "Usia harus minimal 15 tahun",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

        binding.editTxtBirthdate.setOnClickListener {
            showDatePickerDialog()
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

    private fun saveUserData(username: String, email: String, password: String, birthDate: String) {
        val sharedPreferences =
            requireActivity().getSharedPreferences("user_data", Context.MODE_PRIVATE)
        with(sharedPreferences.edit()) {
            putString("username", username)
            putString("email", email)
            putString("password", password)
            putString("birthDate", birthDate)
            apply()
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
