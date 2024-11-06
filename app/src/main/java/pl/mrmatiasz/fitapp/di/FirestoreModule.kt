package pl.mrmatiasz.fitapp.di

import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pl.mrmatiasz.fitapp.data.repository.DatabaseRepositoryImpl
import pl.mrmatiasz.fitapp.domain.repository.DatabaseRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FirestoreModule {

    @Provides
    @Singleton
    fun providesFirebaseFirestore(): FirebaseFirestore = FirebaseFirestore.getInstance()

    @Provides
    @Singleton
    fun providesDatabaseRepository(firebaseFirestore: FirebaseFirestore): DatabaseRepository {
        return DatabaseRepositoryImpl(firebaseFirestore)
    }
}