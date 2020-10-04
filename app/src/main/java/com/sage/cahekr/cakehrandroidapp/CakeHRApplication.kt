package com.sage.cahekr.cakehrandroidapp

import android.app.Application
import com.sage.cahekr.cakehrandroidapp.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class CakeHRApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@CakeHRApplication)
            modules(networkModule)
        }
    }
}