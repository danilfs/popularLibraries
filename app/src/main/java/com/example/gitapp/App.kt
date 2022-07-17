package com.example.gitapp

import android.app.Application
import androidx.fragment.app.Fragment
import androidx.room.Room
import com.example.gitapp.data.CachedUserRepository
import com.example.gitapp.data.FakeUserRepository
import com.example.gitapp.data.retrofit.GithubApiService
import com.example.gitapp.data.retrofit.RetrofitUserRepository
import com.example.gitapp.data.room.RoomUserRepository
import com.example.gitapp.data.room.UserDatabase
import com.example.gitapp.di.appModule
import com.example.gitapp.di.dataModule
import com.example.gitapp.domain.IUserRepository
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class App : Application() {

    lateinit var userRepository: IUserRepository

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(appModule, dataModule)
        }

    }
}