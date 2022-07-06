package com.example.gitapp.domain

import com.example.gitapp.domain.model.User
import io.reactivex.rxjava3.core.Single

interface IUserRepository {
    fun getUser(userId: Int): Single<User>
    fun getUsers(): Single<List<User>>
    fun insert(users: List<User>) {}
}