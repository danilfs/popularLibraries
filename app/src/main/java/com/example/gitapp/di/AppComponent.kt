package com.example.gitapp.di

import com.example.gitapp.ui.userDetails.UserDetailsFragment
import com.example.gitapp.ui.users.UsersFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, DataModule::class])
interface AppComponent {

    fun injectUsersFragment(usersFragment: UsersFragment)

    fun injectUserDetailsFragment(userDetailsFragment: UserDetailsFragment)
}