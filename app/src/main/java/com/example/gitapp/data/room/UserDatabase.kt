package com.chernorotov.gitapp.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.chernorotov.gitapp.data.room.model.UserEntity

@Database(entities = [UserEntity::class], version = 1)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}