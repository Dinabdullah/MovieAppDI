package com.example.movieapp

import android.app.Application
import com.example.movieapp.di.appModule
import org.koin.core.context.GlobalContext.startKoin

class KoinApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
          //  androidLogger()
           // androidContext(this@KoinApplication)
            modules(appModule)

        }
    }
}
