package pl.mrmatiasz.fitapp.domain.use_case.forms_validation

class ValidateConfirmPasswordUseCase {

    fun execute(password: String, confirmPassword: String): ValidationResult {
        if(password != confirmPassword) {
            return ValidationResult(
                isSuccess = false,
                errorMessage = "The passwords don't match"
            )
        }

        return ValidationResult(
            isSuccess = true,
            errorMessage = null
        )
    }
}