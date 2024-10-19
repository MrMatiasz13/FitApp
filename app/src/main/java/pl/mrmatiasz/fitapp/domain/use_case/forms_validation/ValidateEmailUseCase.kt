package pl.mrmatiasz.fitapp.domain.use_case.forms_validation

import android.util.Patterns

class ValidateEmailUseCase {

    fun execute(email: String): ValidationResult {
        if(email.isBlank()) {
            return ValidationResult(
                isSuccess = false,
                errorMessage = "The email can't be blank"
            )
        }

        if(!email.matches(Patterns.EMAIL_ADDRESS.toRegex())) {
            return ValidationResult(
                isSuccess = false,
                errorMessage = "That's not a valid email"
            )
        }

        return ValidationResult(
            isSuccess = true,
            errorMessage = null
        )
    }
}