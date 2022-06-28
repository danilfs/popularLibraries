package com.example.gitapp.domain

import com.example.gitapp.domain.model.User

interface IUserRepository {

    fun getUsers(
        onSuccess: (List<User>) -> Unit,
        onError: (error: Throwable) -> Unit
    )

}