package com.example.getitdone.data.db

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

// Example migration from version 1 to 2
val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE tasks ADD COLUMN pomodoroCount INTEGER NOT NULL DEFAULT 0")
    }
}

// List of all migrations
val ALL_MIGRATIONS = arrayOf(
    MIGRATION_1_2
)
