import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AuthViewModel : ViewModel() {
    private val _navigateToLogin = MutableLiveData<Boolean>()
    val navigateToLogin: LiveData<Boolean> get() = _navigateToLogin

    fun navigateToLogin() {
        _navigateToLogin.value = true
    }

    fun navigationHandled() {
        _navigateToLogin.value = false
    }
}
