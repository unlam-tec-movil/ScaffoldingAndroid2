package ar.edu.unlam.mobile.scaffolding.di

import android.content.Context
import androidx.room.Room
import ar.edu.unlam.mobile.scaffolding.data.datasources.local.Database
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseProvider {
    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context,
    ): Database {
        // Aquí puedes configurar tu base de datos, por ejemplo usando Room
        return Room
            .databaseBuilder(
                context,
                Database::class.java,
                "my_database",
            ).build()
    }

    @Provides
    @Singleton
    fun provideUserDao(database: Database) = database.userDao()

    @Provides
    @Singleton
    fun provideUserBookDao(database: Database) = database.userBookDao()
}
