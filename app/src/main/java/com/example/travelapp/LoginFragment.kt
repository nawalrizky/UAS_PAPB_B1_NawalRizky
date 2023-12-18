package com.example.travelapp
import AuthViewModel
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.travelapp.MainActivity
import com.example.travelapp.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private val authViewModel: AuthViewModel by viewModels()

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
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
                val username = editTxtUsername.text.toString()
                val password = editTxtPassword.text.toString()
                val rememberMe = checkBoxRememberMe.isChecked

                if (validateLogin(username, password)) {
                    // Save the state of the Remember Me checkbox to SharedPreferences
                    with(sharedPreferences.edit()) {
                        putBoolean("rememberMe", rememberMe)
                        apply()
                    }

                    // Navigasi ke halaman utama setelah login berhasil
                    authViewModel.navigateToLogin()
                } else {
                    // Tampilkan pesan kesalahan jika login gagal
                    Toast.makeText(
                        requireContext(),
                        "Login gagal. Periksa kredensial Anda.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            // Tambahkan logika untuk menampilkan/menyembunyikan kata sandi (opsional) di sini.
        }

        // Observe the navigation event
        authViewModel.navigateToLogin.observe(viewLifecycleOwner) { shouldNavigate ->
            if (shouldNavigate) {
                navigateToMainPage()
                authViewModel.navigationHandled()
            }
        }
    }

    private fun validateLogin(username: String, password: String): Boolean {
        // Ambil data pengguna yang telah terdaftar dari SharedPreferences
        val savedUsername = sharedPreferences.getString("username", "")
        val savedPassword = sharedPreferences.getString("password", "")

        // Validasi login
        return username == savedUsername && password == savedPassword
    }

    private fun navigateToMainPage() {
        val intent = Intent(requireContext(), MainActivity::class.java)
        startActivity(intent)
        requireActivity().finish()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
