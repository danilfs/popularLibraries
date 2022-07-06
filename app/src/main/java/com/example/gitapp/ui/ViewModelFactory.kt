package com.example.gitapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.gitapp.domain.IUserRepository
import com.example.gitapp.ui.userDetails.UserDetailsViewModel
import com.example.gitapp.ui.users.UsersViewModel

class ViewModelFactory(private val userRepository: IUserRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        when (modelClass) {
            UsersViewModel::class.java -> UsersViewModel(userRepository) as T
            UserDetailsViewModel::class.java -> UserDetailsViewModel(userRepository) as T
            else -> throw IllegalArgumentException("Unknown ViewModel")
        }
}