package com.example.gitapp

import android.app.Application
import androidx.fragment.app.Fragment
import com.example.gitapp.data.GithubUserRepository
import com.example.gitapp.data.api.GithubApiService
import com.example.gitapp.domain.IUserRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class App: Application() {

    private val githubApiService = Retrofit.Builder()
        .baseUrl("https://api.github.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(GithubApiService::class.java)

    val userRepository: IUserRepository = GithubUserRepository(githubApiService)


}

val Fragment.app: App get() = requireContext().applicationContext as App