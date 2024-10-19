package pl.mrmatiasz.fitapp.domain.use_case.forms_validation

data class ValidationResult(
    val isSuccess: Boolean,
    val errorMessage: String? = null
)
