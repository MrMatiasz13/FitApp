package pl.mrmatiasz.fitapp.presentation.screens.auth.registration_screen

data class RegistrationState(
    val isLoadings: Boolean = false,
    val isSuccessful: String? = "",
    val isError: String? = ""
)
