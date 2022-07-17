package com.example.gitapp.di

import com.example.gitapp.ui.userDetails.UserDetailsViewModel
import com.example.gitapp.ui.users.UsersViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel<UsersViewModel> {
        UsersViewModel(get())
    }

    viewModel<UserDetailsViewModel> {
        UserDetailsViewModel(get())
    }

}