package pl.mrmatiasz.fitapp.domain.repository

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow
import pl.mrmatiasz.fitapp.util.Resource

interface AuthRepository {
    suspend fun login(email: String, password: String): Flow<Resource<AuthResult>>
    suspend fun register(username: String, email: String, password: String): Flow<Resource<AuthResult>>

    fun getCurrentUser(): FirebaseUser
}