package com.example.travelapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.travelapp.databinding.ActivityLoginPageBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            btnLogin.setOnClickListener {
                val username = editTxtUsername.text.toString()
                val password = editTxtPassword.text.toString()

                if (validateLogin(username, password)) {
                    // Navigasi ke halaman utama setelah login berhasil
                    navigateToDasboardPage()
                } else {
                    // Tampilkan pesan kesalahan jika login gagal
                    Toast.makeText(this@LoginActivity, "Login gagal. Periksa kredensial Anda.", Toast.LENGTH_SHORT).show()
                }
            }

            // Tambahkan logika untuk menampilkan/menyembunyikan kata sandi (opsional) di sini.
        }
    }

    private fun validateLogin(username: String, password: String): Boolean {
        // Ambil data pengguna yang telah terdaftar dari SharedPreferences
        val sharedPreferences = getSharedPreferences("user_data", Context.MODE_PRIVATE)
        val savedUsername = sharedPreferences.getString("username", "")
        val savedPassword = sharedPreferences.getString("password", "")

        // Validasi login
        return username == savedUsername && password == savedPassword
    }

    private fun navigateToDasboardPage() {
        val intent = Intent(this, DashboardActivity::class.java)
        startActivity(intent)
    }
}
