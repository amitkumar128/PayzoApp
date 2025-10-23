package com.amit.payzoapp.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class PayzoApp: Application() {
    override fun onCreate() {
        super.onCreate()
    }

}

