package com.example.roomdatabase.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.internal.synchronized


@Database(entities = [User:: class], version = 1, exportSchema = false)

abstract class UserDatabase: RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object{
        @Volatile
        private var INSTANCE: UserDatabase? = null

        fun getDatabase(context: Context): UserDatabase{
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            kotlin.synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserDatabase::class.java,
                    "user_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}