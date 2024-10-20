package pl.mrmatiasz.fitapp.domain.use_case.forms_validation

class ValidateUsernameUseCase {

    fun execute(username: String): ValidationResult {
        if(username.isBlank()) {
            return ValidationResult(
                isSuccess = false,
                errorMessage = "The username can't be blank"
            )
        }

        if(username[0].isLowerCase()) {
            return ValidationResult(
                isSuccess = false,
                errorMessage = "The username must be uppercase"
            )
        }

        return ValidationResult(
            isSuccess = true,
            errorMessage = null
        )
    }
}