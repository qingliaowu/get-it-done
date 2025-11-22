package com.example.getitdone.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "habits")
data class HabitEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val frequency: String, // e.g., "DAILY", "WEEKLY"
    val streak: Int = 0
)
