package com.example.getitdone.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.getitdone.data.entity.TaskEntity
import com.example.getitdone.data.entity.HabitEntity
import com.example.getitdone.data.entity.CategoryEntity
import com.example.getitdone.data.entity.PetEntity
import com.example.getitdone.data.entity.MoodEntity

// We will add entities as we create them. For now, I'll create placeholders or comment them out to avoid compilation errors if files don't exist yet.
// Actually, I should create the entities first or empty classes.
// Let's create the Database class assuming entities will be there. I will create entities in the next step.

@Database(
    entities = [
        TaskEntity::class,
        HabitEntity::class,
        CategoryEntity::class,
        PetEntity::class,
        MoodEntity::class
    ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun taskDao(): com.example.getitdone.data.dao.TaskDao
    abstract fun habitDao(): com.example.getitdone.data.dao.HabitDao
    abstract fun categoryDao(): com.example.getitdone.data.dao.CategoryDao
    abstract fun petDao(): com.example.getitdone.data.dao.PetDao
    abstract fun moodDao(): com.example.getitdone.data.dao.MoodDao
}
