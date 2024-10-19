package pl.mrmatiasz.fitapp.domain.use_case.forms_validation

class ValidateRepeatedPasswordUseCase {

    fun execute(password: String, repeatedPassword: String): ValidationResult {
        if(password != repeatedPassword) {
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