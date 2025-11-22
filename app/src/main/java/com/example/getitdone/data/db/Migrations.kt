package com.example.getitdone.data.db

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

// Example migration from version 1 to 2
val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        // Example: database.execSQL("ALTER TABLE tasks ADD COLUMN priority INTEGER DEFAULT 0 NOT NULL")
    }
}

// List of all migrations
val ALL_MIGRATIONS = arrayOf<Migration>()
