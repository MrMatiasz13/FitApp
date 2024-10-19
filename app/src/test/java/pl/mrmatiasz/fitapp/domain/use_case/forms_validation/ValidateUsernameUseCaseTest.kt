package pl.mrmatiasz.fitapp.domain.use_case.forms_validation

import org.junit.jupiter.api.Assertions.*

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class ValidateUsernameUseCaseTest {

    private lateinit var usernameValidation: ValidateUsernameUseCase

    @BeforeEach
    fun setUp() {
        usernameValidation = ValidateUsernameUseCase()
    }

    @Test
    fun `username is lowercase, returns error`() {
        val result = usernameValidation.execute("username")

        assertEquals(false, result.isSuccess)
    }
}