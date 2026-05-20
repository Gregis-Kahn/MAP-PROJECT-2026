package com.example.valentinesgarage.data.entities

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.valentinesgarage.data.local.dao.TaskDao
import com.example.valentinesgarage.data.local.dao.TruckDao
import com.example.valentinesgarage.data.local.dao.UserDao

@Database(
    entities = [
        Truck::class,
        Task::class,
        User::class
    ],
    version = 1
)
abstract class GarageDatabase : RoomDatabase() {

    abstract fun truckDao(): TruckDao

    abstract fun userDao(): UserDao

    abstract fun taskDao(): TaskDao

    companion object {

        @Volatile
        private var INSTANCE: GarageDatabase? = null

        fun getDatabase(context: Context): GarageDatabase {

            return INSTANCE ?: synchronized(this) {

                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    GarageDatabase::class.java,
                    "garage_db"
                ).build()

                INSTANCE = instance

                instance
            }
        }
    }
}