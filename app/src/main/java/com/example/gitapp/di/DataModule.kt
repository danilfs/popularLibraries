package com.example.gitapp.di

import androidx.room.Room
import com.example.gitapp.data.CachedUserRepository
import com.example.gitapp.data.retrofit.GithubApiService
import com.example.gitapp.data.retrofit.RetrofitUserRepository
import com.example.gitapp.data.room.RoomUserRepository
import com.example.gitapp.data.room.UserDao
import com.example.gitapp.data.room.UserDatabase
import com.example.gitapp.domain.IUserRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val dataModule = module {

    val baseUrl = "https://api.github.com/"

    single<UserDatabase> {
        Room.databaseBuilder(
            get(),
            UserDatabase::class.java,
            "users.db"
        ).build()
    }

    single<UserDao> {
        get<UserDatabase>().userDao()
    }

    single<OkHttpClient> {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }

    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
    }

    single<GithubApiService> {
        get<Retrofit>().create(GithubApiService::class.java)
    }

    single<IUserRepository> {
        val retrofitUserRepository = RetrofitUserRepository(get())
        val roomUserRepository = RoomUserRepository(get())
        CachedUserRepository(retrofitUserRepository, roomUserRepository)
    }

}