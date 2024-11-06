package pl.mrmatiasz.fitapp.domain.repository

import pl.mrmatiasz.fitapp.data.model.User

interface DatabaseRepository {
    suspend fun addUserToDb(user: User)
}