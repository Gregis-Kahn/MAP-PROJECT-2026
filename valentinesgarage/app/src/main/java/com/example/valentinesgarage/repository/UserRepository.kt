package com.example.valentinesgarage.repository

import com.example.valentinesgarage.data.entities.User
import com.example.valentinesgarage.data.local.dao.UserDao

class UserRepository(
    private val dao: UserDao
) {

    val allUsers = dao.getAllUsers()

    suspend fun insertUser(user: User) {
        dao.insertUser(user)
    }

    suspend fun login(
        email: String,
        password: String
    ): User? {

        return dao.login(email, password)
    }
}