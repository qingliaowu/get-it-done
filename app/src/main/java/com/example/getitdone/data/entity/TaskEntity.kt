package com.example.getitdone.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class TaskEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val description: String,
    val isCompleted: Boolean = false,
    val dueDate: Long? = null,
    val categoryId: Int? = null,
    val pomodoroCount: Int = 0
)
