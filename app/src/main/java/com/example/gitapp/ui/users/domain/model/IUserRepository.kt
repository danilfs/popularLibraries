package com.example.gitapp.ui.users.domain.model

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

interface IUserRepository {

    fun getUsers(
        onSuccess: (List<User>) -> Unit,
        onError: (error: Throwable) -> Unit
    )

}