package com.example.gitapp.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.gitapp.data.room.mappers.UserEntity

@Database(entities = [UserEntity::class], version = 1)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}