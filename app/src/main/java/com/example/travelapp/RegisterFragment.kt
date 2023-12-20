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
import com.example.travelapp.MainActivity
import com.example.travelapp.databinding.FragmentRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import java.text.SimpleDateFormat
import java.util.*
import com.google.firebase.database.FirebaseDatabase

class RegisterFragment : Fragment() {
    private val authViewModel: AuthViewModel by activityViewModels()
    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    private val calendar = Calendar.getInstance()
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        auth = FirebaseAuth.getInstance()
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

                // Register user with Firebase Authentication
                registerUser(username,email, password, birthDateDate)
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

    private fun registerUser(username: String,email: String, password: String, birthDate: Date) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    // Registration success
                    saveUserData(username,email, password, birthDate)

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

    private fun saveUserData(username: String, email: String, password: String, birthDate: Date) {
        val userId = FirebaseAuth.getInstance().currentUser?.uid
        if (userId != null) {
            val database = FirebaseDatabase.getInstance()
            val usersRef = database.getReference("users")

            val user = hashMapOf(
                "username" to username,
                "email" to email,
                "password" to password,
                "birthDate" to birthDate.toString(), // Store the birth date as a string for simplicity
                // Add other user data as needed
            )

            usersRef.child(userId).setValue(user)
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
