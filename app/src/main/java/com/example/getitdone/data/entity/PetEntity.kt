package com.example.getitdone.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pet_state")
data class PetEntity(
    @PrimaryKey val id: Int = 0, // Singleton row
    val name: String,
    val hunger: Int, // 0-100
    val happiness: Int, // 0-100
    val experience: Int,
    val level: Int
)
