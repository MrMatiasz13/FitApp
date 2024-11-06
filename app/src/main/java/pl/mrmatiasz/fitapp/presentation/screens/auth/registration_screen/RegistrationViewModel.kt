package pl.mrmatiasz.fitapp.presentation.screens.auth.registration_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import pl.mrmatiasz.fitapp.domain.repository.AuthRepository
import pl.mrmatiasz.fitapp.domain.use_case.forms_validation.ValidateConfirmPasswordUseCase
import pl.mrmatiasz.fitapp.domain.use_case.forms_validation.ValidateEmailUseCase
import pl.mrmatiasz.fitapp.domain.use_case.forms_validation.ValidatePasswordUseCase
import pl.mrmatiasz.fitapp.domain.use_case.forms_validation.ValidateUsernameUseCase
import pl.mrmatiasz.fitapp.presentation.screens.auth.FormEvent
import pl.mrmatiasz.fitapp.presentation.screens.auth.login_screen.LoginState
import pl.mrmatiasz.fitapp.util.Resource
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val validateUsername: ValidateUsernameUseCase,
    private val validateEmail: ValidateEmailUseCase,
    private val validatePassword: ValidatePasswordUseCase,
    private val validateConfirmPassword: ValidateConfirmPasswordUseCase,
    private val authRepository: AuthRepository,
): ViewModel() {
    var formState by mutableStateOf(RegistrationFormState())

    private var _registrationState = MutableStateFlow(RegistrationState())
    val registrationState: StateFlow<RegistrationState> = _registrationState

    fun onEvent(event: FormEvent) {
        when(event) {
            is FormEvent.UsernameChanged -> {
                formState = formState.copy(username = event.username)
                validateUsername()
            }

            is FormEvent.EmailChanged -> {
                formState = formState.copy(email = event.email)
                validateEmail()
            }

            is FormEvent.PasswordChanged -> {
                formState = formState.copy(password = event.password)
                validatePassword()
            }

            is FormEvent.ConfirmPasswordChanged -> {
                formState = formState.copy(confirmPassword = event.confirmPassword)
                validateConfirmPassword()
            }

            is FormEvent.Submit -> {
                val hasError = listOf(
                    validateUsername(),
                    validateEmail(),
                    validatePassword(),
                    validateConfirmPassword()
                ).any { !it }

                if(!hasError) {
                    register(formState.username, formState.email, formState.password)
                }
            }
        }
    }

    private fun validateUsername(): Boolean {
        val result = validateUsername.execute(formState.username)
        formState = formState.copy(usernameError = result.errorMessage)
        return result.isSuccess
    }

    private fun validateEmail(): Boolean {
        val result = validateEmail.execute(formState.email)
        formState = formState.copy(emailError = result.errorMessage)
        return result.isSuccess
    }

    private fun validatePassword(): Boolean {
        val result = validatePassword.execute(formState.password)
        formState = formState.copy(passwordError = result.errorMessage)
        return result.isSuccess
    }

    private fun validateConfirmPassword(): Boolean {
        val result = validateConfirmPassword.execute(formState.password, formState.confirmPassword)
        formState = formState.copy(confirmPasswordError = result.errorMessage)
        return result.isSuccess
    }

    private fun register(username: String, email: String, password: String) {
        viewModelScope.launch {
            authRepository.register(username, email, password).collect { result ->
                when(result) {
                    is Resource.Loading -> _registrationState.emit(RegistrationState(isLoading = true))

                    is Resource.Success -> _registrationState.emit(RegistrationState(isSuccessful = "Registration is successful!"))

                    is Resource.Error -> {
                        _registrationState.emit(RegistrationState(isError = result.message))
                    }
                }
            }
        }
    }
}