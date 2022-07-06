package com.example.gitapp.domain

import com.example.gitapp.domain.model.User

interface IUserRepository {

    fun getUser(
        userId: Int,
        onSuccess: (User) -> Unit,
        onError: (error: Throwable) -> Unit
    )

    fun getUsers(
        onSuccess: (List<User>) -> Unit,
        onError: (error: Throwable) -> Unit
    )

}