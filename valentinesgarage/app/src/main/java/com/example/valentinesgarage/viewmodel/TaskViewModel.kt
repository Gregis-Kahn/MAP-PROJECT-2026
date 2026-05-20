package com.example.valentinesgarage.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.valentinesgarage.data.entities.GarageDatabase
import com.example.valentinesgarage.data.entities.Task
import kotlinx.coroutines.launch

class TaskViewModel(application: Application) : AndroidViewModel(application) {

    private val dao =
        GarageDatabase.getDatabase(application).taskDao()

    val allTasks =
        dao.getAllTasks()

    fun getTasks(truckId: Int) =
        dao.getTasksForTruck(truckId)

    fun addTask(task: Task) {

        viewModelScope.launch {
            dao.insertTask(task)
        }
    }

    fun updateTask(task: Task) {

        viewModelScope.launch {
            dao.updateTask(task)
        }
    }
}