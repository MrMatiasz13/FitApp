package pl.mrmatiasz.fitapp.data.repository

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import pl.mrmatiasz.fitapp.data.model.User
import pl.mrmatiasz.fitapp.domain.repository.DatabaseRepository
import javax.inject.Inject

class DatabaseRepositoryImpl @Inject constructor(
    private val db: FirebaseFirestore
): DatabaseRepository {

    override suspend fun addUserToDb(user: User) {
        if(checkIfUserExists(user)) {
            return
        }

        val userMap = hashMapOf(
            "userUId" to user.userUId,
            "username" to user.username,
            "email" to user.email
        )

        db.collection("users")
            .add(userMap)
            .addOnSuccessListener {
                Log.d("database", "User added to db")
            }
            .addOnFailureListener { e ->
                Log.d("database", "Error adding user to db: $e")
            }
            .await()
    }

    private suspend fun checkIfUserExists(user: User): Boolean {
        val query = db.collection("users")
            .whereEqualTo("userUId", user.userUId)
            .get()
            .await()

        return !query.isEmpty
    }
}