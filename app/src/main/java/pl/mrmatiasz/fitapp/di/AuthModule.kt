package pl.mrmatiasz.fitapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pl.mrmatiasz.fitapp.domain.use_case.forms_validation.ValidateConfirmPasswordUseCase
import pl.mrmatiasz.fitapp.domain.use_case.forms_validation.ValidateEmailUseCase
import pl.mrmatiasz.fitapp.domain.use_case.forms_validation.ValidatePasswordUseCase
import pl.mrmatiasz.fitapp.domain.use_case.forms_validation.ValidateUsernameUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {

    @Provides
    @Singleton
    fun provideValidateUsernameUseCase(): ValidateUsernameUseCase {
        return ValidateUsernameUseCase()
    }

    @Provides
    @Singleton
    fun provideValidateEmailUseCase(): ValidateEmailUseCase {
        return ValidateEmailUseCase()
    }

    @Provides
    @Singleton
    fun provideValidatePasswordUseCase(): ValidatePasswordUseCase {
        return ValidatePasswordUseCase()
    }

    @Provides
    @Singleton
    fun provideValidateConfirmPasswordUseCase(): ValidateConfirmPasswordUseCase {
        return ValidateConfirmPasswordUseCase()
    }
}