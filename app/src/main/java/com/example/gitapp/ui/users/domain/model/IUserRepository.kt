package com.example.gitapp.ui.users.domain.model

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

interface IUserRepository {

    fun getUsers(): Flow<PagingData<User>>

}