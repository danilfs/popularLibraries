package com.example.gitapp

import android.app.Application
import ru.gidural.mykoin.startKoin


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
