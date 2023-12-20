import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.travelapp.MainActivity
import com.example.travelapp.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private val authViewModel: AuthViewModel by viewModels()
    private lateinit var auth: FirebaseAuth

    private lateinit var sharedPreferences: SharedPreferences

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

                    // Navigation to the main page after successful login
                    authViewModel.navigateToLogin()
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
