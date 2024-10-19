package pl.mrmatiasz.fitapp.presentation.screens.auth.login_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import pl.mrmatiasz.fitapp.domain.use_case.forms_validation.ValidateEmailUseCase
import pl.mrmatiasz.fitapp.domain.use_case.forms_validation.ValidatePasswordUseCase
import pl.mrmatiasz.fitapp.presentation.screens.auth.FormEvent
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val validateEmail: ValidateEmailUseCase,
    private val validatePassword: ValidatePasswordUseCase
): ViewModel() {
    var formState by mutableStateOf(LoginFormState())

    fun onEvent(event: FormEvent) {
        when(event) {
            is FormEvent.EmailChanged -> {
                formState = formState.copy(email = event.email)
            }

            is FormEvent.PasswordChanged -> {
                formState = formState.copy(password = event.password)
            }

            is FormEvent.Submit -> {
                val hasError = listOf(
                    validateEmail(),
                    validatePassword()
                ).any { !it }

                if(!hasError) {
                    /*TODO*/
                }
            }

            else -> {}
        }
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
}