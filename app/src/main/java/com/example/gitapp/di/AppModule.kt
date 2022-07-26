package com.example.gitapp

import ru.gidural.mykoin.get
import ru.gidural.mykoin.module


val appModule = module {

    viewModel<UsersViewModel> {
        UsersViewModel(get())
    }

    viewModel<UserDetailsViewModel> {
        UserDetailsViewModel(get())
    }

}