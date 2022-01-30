package com.samuelnunes.too_dooapp

import android.app.Application
import timber.log.Timber

class TodoApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}