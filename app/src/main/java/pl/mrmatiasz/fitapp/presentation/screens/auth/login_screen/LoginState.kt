package pl.mrmatiasz.fitapp.presentation.screens.auth.login_screen

data class LoginState(
    val isLoading: Boolean = false,
    val isSuccessful: String? = null,
    val isError: String? = null
)