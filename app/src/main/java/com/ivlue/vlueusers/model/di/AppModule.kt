package com.ivlue.vlueusers.model.di

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class AppModule: Application() {
    override fun onCreate() {
        super.onCreate()
    }
}