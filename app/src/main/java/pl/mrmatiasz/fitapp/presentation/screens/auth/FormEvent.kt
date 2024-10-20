package pl.mrmatiasz.fitapp.presentation.screens.auth

sealed class FormEvent {
    data class UsernameChanged(val username: String): FormEvent()
    data class EmailChanged(val email: String): FormEvent()
    data class PasswordChanged(val password: String): FormEvent()
    data class ConfirmPasswordChanged(val confirmPassword: String): FormEvent()
    data object Submit: FormEvent()
}