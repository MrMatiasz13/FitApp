package pl.mrmatiasz.fitapp.presentation.screens.auth.login_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import pl.mrmatiasz.fitapp.data.model.User
import pl.mrmatiasz.fitapp.domain.repository.AuthRepository
import pl.mrmatiasz.fitapp.domain.repository.DatabaseRepository
import pl.mrmatiasz.fitapp.presentation.screens.auth.FormEvent
import pl.mrmatiasz.fitapp.util.Resource
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val databaseRepository: DatabaseRepository,
): ViewModel() {
    var formState by mutableStateOf(LoginFormState())

    private var _loginState = MutableStateFlow(LoginState())
    val loginState: StateFlow<LoginState> = _loginState

    fun onEvent(event: FormEvent) {
        when(event) {
            is FormEvent.EmailChanged -> {
                formState = formState.copy(email = event.email)
            }

            is FormEvent.PasswordChanged -> {
                formState = formState.copy(password = event.password)
            }

            is FormEvent.Submit -> {
                login(formState.email, formState.password)
            }

            else -> {}
        }
    }

    private fun login(email: String, password: String) {
        viewModelScope.launch {
            authRepository.login(email, password).collect { result ->
                when(result) {
                    is Resource.Loading -> _loginState.emit(LoginState(isLoading = true))

                    is Resource.Success -> {
                        _loginState.emit(LoginState(isSuccessful = "Login is successful!"))
                        val currentUser = authRepository.getCurrentUser()
                        val user = User(
                            userUId = currentUser.uid,
                            username = currentUser.displayName.toString(),
                            email = currentUser.email.toString()
                        )
                        databaseRepository.addUserToDb(user)
                    }

                    is Resource.Error -> {
                        _loginState.emit(LoginState(isError = result.message))
                    }
                }
            }
        }
    }
}