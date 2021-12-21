package com.ramazan.composeapp

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(listOf(appModule))
        }
    }

    private val appModule = module {
        single<TextRepository> {
            TextRepositoryImpl(get())
        }

        single {
            arrayListOf(
                "Monday",
                "Tuesday",
                "Wednesday",
                "Thursday",
                "Friday",
                "Saturday",
                "Sunday"
            )
        }

        viewModel {
            MainViewModel(get())
        }
    }

}