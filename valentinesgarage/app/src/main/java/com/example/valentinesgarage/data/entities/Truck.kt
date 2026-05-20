package com.example.valentinesgarage.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "trucks")
data class Truck(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val truckName: String,
    val ownerName: String,

    val kilometers: Int,

    val condition: String,

    val issue: String,

    val checkInDate: Long = System.currentTimeMillis()
)