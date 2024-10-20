package pl.mrmatiasz.fitapp.domain.use_case.forms_validation

import org.junit.jupiter.api.Assertions.*

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class ValidateEmailUseCaseTest {

    private lateinit var emailValidation: ValidateEmailUseCase

    @BeforeEach
    fun setUp() {
        emailValidation = ValidateEmailUseCase()
    }

    @Test
    fun `email isn't valid, return error`() {
        val result = emailValidation.execute("email")

        assertEquals(false, result.isSuccess)
    }

    @Test
    fun `email is valid, return success`() {
        val result = emailValidation.execute("email@gmail.com")

        assertEquals(true, result.isSuccess)
    }
}