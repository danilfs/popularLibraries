package com.example.gitapp.ui.users

import com.example.gitapp.domain.model.User


sealed class UsersViewState {
    object Loading: UsersViewState()
    data class Success(val users: List<User>): UsersViewState()
    data class Error(val error: Throwable): UsersViewState()
}