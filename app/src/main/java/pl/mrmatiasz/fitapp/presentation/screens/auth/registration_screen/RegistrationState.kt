package pl.mrmatiasz.fitapp.presentation.screens.auth.registration_screen

data class RegistrationState(
    val isLoading: Boolean = false,
    val isSuccessful: String? = "",
    val isError: String? = ""
)
