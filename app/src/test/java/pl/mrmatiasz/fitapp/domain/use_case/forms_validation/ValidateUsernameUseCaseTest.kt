package pl.mrmatiasz.fitapp.domain.use_case.forms_validation

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class ValidateUsernameUseCaseTest {

    private lateinit var validateUsernameUseCase: ValidateUsernameUseCase

    @BeforeEach
    fun setUp() {
        validateUsernameUseCase = ValidateUsernameUseCase()
    }

    @Test
    fun `username is lowercase, returns error`() {
        val result = validateUsernameUseCase.execute("username")

        assert(!result.isSuccess)
    }
}