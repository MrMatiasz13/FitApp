package pl.mrmatiasz.fitapp.domain.use_case.forms_validation

import android.util.Patterns
import java.util.regex.Pattern

class ValidateEmailUseCase {

    fun execute(email: String): ValidationResult {
        val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\$"

        if(email.isBlank()) {
            return ValidationResult(
                isSuccess = false,
                errorMessage = "The email can't be blank"
            )
        }

        if(!email.matches(emailRegex.toRegex())) {
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