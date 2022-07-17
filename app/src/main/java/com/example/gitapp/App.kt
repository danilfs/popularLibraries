package com.example.gitapp

import android.app.Application
import androidx.fragment.app.Fragment
import com.example.gitapp.di.AppComponent
import com.example.gitapp.di.AppModule
import com.example.gitapp.di.DaggerAppComponent



class App : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent
            .builder()
            .appModule(AppModule(this))
            .build()


    }
}


val Fragment.appComponent get() = (requireContext().applicationContext as App).appComponent