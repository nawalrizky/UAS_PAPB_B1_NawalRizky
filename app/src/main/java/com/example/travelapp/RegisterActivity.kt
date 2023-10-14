package com.example.travelapp

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.travelapp.databinding.ActivityRegisterPageBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterPageBinding
    private val calendar = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            btnRegister.setOnClickListener {
                val username = editTxtUsername.text.toString()
                val email = editTxtEmail.text.toString()
                val password = editTxtPassword.text.toString()
                val birthDate = editTxtBirthdate.text.toString()

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

                        // Navigasi ke halaman login setelah pendaftaran berhasil
                        navigateToLoginPage()
                    } else {
                        // Tampilkan pesan kesalahan jika usia tidak memenuhi syarat
                        Toast.makeText(
                            this@RegisterActivity,
                            "Usia harus minimal 15 tahun",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
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
            Toast.makeText(this, "Semua kolom harus diisi", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    private fun saveUserData(username: String, email: String, password: String, birthDate: String) {
        val sharedPreferences = getSharedPreferences("user_data", Context.MODE_PRIVATE)
        with(sharedPreferences.edit()) {
            putString("username", username)
            putString("email", email)
            putString("password", password)
            putString("birthDate", birthDate)
            apply()
        }
    }

    private fun navigateToLoginPage() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }

    fun showDatePickerDialog(view: View) {
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { _, selectedYear, selectedMonth, selectedDay ->
                calendar.set(selectedYear, selectedMonth, selectedDay)
                val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                binding.editTxtBirthdate.setText(sdf.format(calendar.time))
            },
            year,
            month,
            day
        )
        datePickerDialog.datePicker.maxDate = System.currentTimeMillis() - 1000L * 60 * 60 * 24 * 365 * 15 // 15 years ago
        datePickerDialog.show()
    }
}
