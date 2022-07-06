package com.example.gitapp

import android.app.Application
import androidx.fragment.app.Fragment
import com.example.gitapp.data.FakeUserRepository
import com.example.gitapp.data.retrofit.GithubApiService
import com.example.gitapp.domain.IUserRepository
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class App: Application() {

    // TODO(use di to create rest service)
    private val githubApiService = Retrofit.Builder()
        .baseUrl("https://api.github.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()
        .create(GithubApiService::class.java)

    // TODO(use di to create a repository)
    val userRepository: IUserRepository = //RetrofitUserRepository(githubApiService)
        FakeUserRepository()

}

val Fragment.app: App get() = requireContext().applicationContext as App