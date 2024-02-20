package com.valentinerutto.shamiri

import android.app.Application
import com.valentinerutto.shamiri.di.networkingModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        val modules = listOf(networkingModule)

        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(modules)
        }

    }
}