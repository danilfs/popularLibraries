package com.example.gitapp

import android.app.Application
import androidx.fragment.app.Fragment
import com.example.gitapp.data.FakeUserRepository
import com.example.gitapp.data.api.GithubApiService
import com.example.gitapp.domain.IUserRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class App: Application() {

    // TODO(use di to create rest service)
    private val githubApiService = Retrofit.Builder()
        .baseUrl("https://api.github.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(GithubApiService::class.java)

    // TODO(use di to create a repository)
    private val userRepository: IUserRepository = //GithubUserRepository(githubApiService)
                                                  FakeUserRepository()

    // TODO(use di to create a presenter)
    val usersPresenter = UsersPresenter(userRepository)

}

val Fragment.app: App get() = requireContext().applicationContext as App