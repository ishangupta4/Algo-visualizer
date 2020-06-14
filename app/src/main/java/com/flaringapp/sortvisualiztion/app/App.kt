package com.flaringapp.sortvisualiztion.app

import android.app.Application
import com.flaringapp.sortvisualiztion.app.di.app_modules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)

            androidLogger()

            modules(app_modules)
        }
    }
}