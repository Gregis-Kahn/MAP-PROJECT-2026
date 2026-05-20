package com.example.valentinesgarage.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val truckId: Int,

    val taskName: String,

    val isDone: Boolean = false,

    val notes: String = ""
)