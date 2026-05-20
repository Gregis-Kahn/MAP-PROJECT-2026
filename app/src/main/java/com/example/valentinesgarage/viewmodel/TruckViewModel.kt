package com.example.valentinesgarage.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.valentinesgarage.data.entities.GarageDatabase
import com.example.valentinesgarage.data.entities.Truck
import com.example.valentinesgarage.repository.TruckRepository
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class TruckViewModel(application: Application) : AndroidViewModel(application) {

    private val dao = GarageDatabase.getDatabase(application).truckDao()
    private val repository = TruckRepository(dao)

    val allTrucks: StateFlow<List<Truck>> =
        repository.allTrucks
            .stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(5000),
                emptyList()
            )


    fun insertTruck(truck: Truck) {
        viewModelScope.launch {
            repository.insertTruck(truck)
        }
    }
}