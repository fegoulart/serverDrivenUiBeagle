package com.leapi.beagletest

import android.app.Application

class AppApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        BeagleSetup().init(this)
    }
}