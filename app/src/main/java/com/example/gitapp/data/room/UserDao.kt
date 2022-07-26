package com.chernorotov.gitapp.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.chernorotov.gitapp.data.room.model.UserEntity
import io.reactivex.rxjava3.core.Flowable

@Dao
interface UserDao {

    @Query("SELECT * FROM users")
    fun getUsers(): Flowable<List<UserEntity>>

    @Query("SELECT * FROM users WHERE id = :userId")
    fun getUser(userId: Int): Flowable<UserEntity>

    @Insert(onConflict = REPLACE)
    fun insert(users: List<UserEntity>)

}