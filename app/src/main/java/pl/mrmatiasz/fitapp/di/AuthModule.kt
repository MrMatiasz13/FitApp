package pl.mrmatiasz.fitapp.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pl.mrmatiasz.fitapp.data.repository.AuthRepositoryImpl
import pl.mrmatiasz.fitapp.domain.repository.AuthRepository
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
    fun providesFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun provideAuthRepository(firebaseAuth: FirebaseAuth): AuthRepository {
        return AuthRepositoryImpl(firebaseAuth)
    }

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