package com.example.gitapp.di

import android.content.Context
import com.example.gitapp.domain.IUserRepository
import com.example.gitapp.ui.userDetails.UserDetailsViewModel
import com.example.gitapp.ui.users.UsersViewModel
import dagger.Module
import dagger.Provides


@Module
class AppModule(private val context: Context) {

    @Provides
    fun provideContext(): Context = context

    @Provides
    fun provideUsersViewModel(userRepository: IUserRepository) =
        UsersViewModel(userRepository)

    @Provides
    fun provideUserDetailsViewModel(userRepository: IUserRepository) =
        UserDetailsViewModel(userRepository)

}