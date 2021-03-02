package com.pedroluis.fastshoptest

import android.app.Application
import com.pedroluis.fastshoptest.di.apiModule
import com.pedroluis.fastshoptest.di.dataModule
import com.pedroluis.fastshoptest.di.repositoryModule
import com.pedroluis.fastshoptest.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class CustomApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@CustomApplication)
            modules(apiModule, dataModule, repositoryModule, viewModelModule)
        }
    }
}