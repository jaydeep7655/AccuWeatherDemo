package com.example.accuweatherdemo

import android.app.Application
import com.example.accuweatherdemo.di.AppModule
import com.example.accuweatherdemo.di.NetwokModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@MainApplication)
            modules(listOf(AppModule, NetwokModule))
        }
    }
}