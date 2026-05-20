package com.example.valentinesgarage.data.local.dao

import androidx.room.*
import com.example.valentinesgarage.data.entities.Truck
import kotlinx.coroutines.flow.Flow

@Dao
interface TruckDao {

    @Insert
    suspend fun insertTruck(truck: Truck)

    @Update
    suspend fun updateTruck(truck: Truck)

    @Delete
    suspend fun deleteTruck(truck: Truck)

    @Query("SELECT * FROM trucks ORDER BY id DESC")
    fun getAllTrucks(): Flow<List<Truck>>
}