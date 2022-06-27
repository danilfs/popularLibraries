package com.example.gitapp.ui.users

import android.app.Application
import androidx.fragment.app.Fragment
import com.example.gitapp.ui.users.data.FakeUserRepository
import com.example.gitapp.ui.users.domain.model.IUserRepository

class App: Application() {

    val userRepository: IUserRepository = FakeUserRepository()

}

val Fragment.app: App get() = requireContext().applicationContext as App