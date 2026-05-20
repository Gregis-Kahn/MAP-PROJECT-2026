package com.example.valentinesgarage.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.valentinesgarage.data.entities.GarageDatabase
import com.example.valentinesgarage.data.entities.User
import com.example.valentinesgarage.repository.UserRepository
import kotlinx.coroutines.launch

class UserViewModel(application: Application)
    : AndroidViewModel(application) {

    private val dao =
        GarageDatabase.getDatabase(application).userDao()

    private val repository =
        UserRepository(dao)

    fun insertUser(user: User) {

        viewModelScope.launch {
            repository.insertUser(user)
        }
    }

    suspend fun login(
        email: String,
        password: String
    ): User? {

        return repository.login(email, password)
    }
}