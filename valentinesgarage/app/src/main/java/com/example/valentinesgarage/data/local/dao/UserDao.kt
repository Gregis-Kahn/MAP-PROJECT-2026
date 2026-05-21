package com.example.valentinesgarage.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.valentinesgarage.data.entities.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Insert
    suspend fun insertUser(user: User)

    @Query(
        "SELECT * FROM users WHERE email = :email AND password = :password LIMIT 1"
    )
    suspend fun login(
        email: String,
        password: String
    ): User?

    @Query("SELECT * FROM users")
    fun getAllUsers(): Flow<List<User>>
}