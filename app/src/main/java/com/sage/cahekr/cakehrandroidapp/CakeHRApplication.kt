package com.sage.cahekr.cakehrandroidapp

import android.app.Application
import com.sage.cahekr.cakehrandroidapp.data.dataModule
import com.sage.cahekr.cakehrandroidapp.domain.domainModule
import com.sage.cahekr.cakehrandroidapp.presentation.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class CakeHRApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@CakeHRApplication)
            modules(listOf(presentationModule, domainModule, dataModule))
        }
    }
}
