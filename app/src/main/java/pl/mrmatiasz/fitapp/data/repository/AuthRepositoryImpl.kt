package pl.mrmatiasz.fitapp.data.repository

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import pl.mrmatiasz.fitapp.domain.repository.AuthRepository
import pl.mrmatiasz.fitapp.util.Resource
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
): AuthRepository {
    override suspend fun login(email: String, password: String): Flow<Resource<AuthResult>> {
        return flow {
            emit(Resource.Loading())
            try {
                val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()

                val user = result.user
                if(user!!.isEmailVerified) {
                    emit(Resource.Success(result))
                } else {
                    firebaseAuth.signOut()
                    emit(Resource.Error("Email is not verified. Please verify your email before logging in."))
                }
            } catch (e: Exception) {
                emit(Resource.Error(e.message ?: "An unexpected error occurred"))
            }
        }
    }

    override suspend fun register(
        username: String,
        email: String,
        password: String
    ): Flow<Resource<AuthResult>> {
        return flow {
            emit(Resource.Loading())
            try {
                val result = firebaseAuth.createUserWithEmailAndPassword(email, password).addOnSuccessListener {
                    it.user!!.updateProfile(UserProfileChangeRequest.Builder()
                        .setDisplayName(username)
                        .build())
                    it.user!!.sendEmailVerification()
                }.await()
                emit(Resource.Success(result))
            } catch (e: Exception) {
                emit(Resource.Error(e.message ?: "An unexpected error occurred"))
            }
        }
    }

    override fun getCurrentUser(): FirebaseUser {
        return firebaseAuth.currentUser!!
    }
}