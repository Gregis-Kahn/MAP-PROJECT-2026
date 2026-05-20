package com.example.valentinesgarage.repository

import com.example.valentinesgarage.data.local.dao.TruckDao
import com.example.valentinesgarage.data.entities.Truck

class TruckRepository(
    private val dao: TruckDao
) {

    val allTrucks = dao.getAllTrucks()

    suspend fun insertTruck(truck: Truck) {
        dao.insertTruck(truck)
    }

    suspend fun updateTruck(truck: Truck) {
        dao.updateTruck(truck)
    }

    suspend fun deleteTruck(truck: Truck) {
        dao.deleteTruck(truck)
    }
}