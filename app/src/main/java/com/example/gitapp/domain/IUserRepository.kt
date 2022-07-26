package com.example.gitapp


import io.reactivex.rxjava3.core.Flowable

interface IUserRepository {
    fun getUser(userId: Int): Flowable<User>
    fun getUsers(): Flowable<List<User>>
    fun insert(users: List<User>) {}
}