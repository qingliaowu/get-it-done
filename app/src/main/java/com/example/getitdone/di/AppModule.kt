package com.example.getitdone.di

import android.content.Context
import androidx.room.Room
import com.example.getitdone.data.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

import com.example.getitdone.data.db.ALL_MIGRATIONS

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "get_it_done_db"
        )
        .addMigrations(*ALL_MIGRATIONS)
        .build()
    }

    @Provides
    fun provideTaskDao(database: AppDatabase) = database.taskDao()

    @Provides
    fun provideHabitDao(database: AppDatabase) = database.habitDao()

    @Provides
    fun provideCategoryDao(database: AppDatabase) = database.categoryDao()

    @Provides
    fun providePetDao(database: AppDatabase) = database.petDao()

    @Provides
    fun provideMoodDao(database: AppDatabase) = database.moodDao()
}
