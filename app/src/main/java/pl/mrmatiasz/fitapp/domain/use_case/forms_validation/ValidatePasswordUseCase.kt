package pl.mrmatiasz.fitapp.domain.use_case.forms_validation

class ValidatePasswordUseCase {

    fun execute(password: String): ValidationResult {
        if(password.isBlank()) {
            return ValidationResult(
                isSuccess = false,
                errorMessage = "The password can't be blank"
            )
        }

        if(password.length < 8) {
            return ValidationResult(
                isSuccess = false,
                errorMessage = "The password needs at least 8 characters"
            )
        }

        return ValidationResult(
            isSuccess = true,
            errorMessage = null
        )
    }
}