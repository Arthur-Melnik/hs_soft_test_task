package com.hssoft.counries

import android.app.Application
import com.hssoft.counries.di.ApplicationComponent
import com.hssoft.counries.di.DaggerApplicationComponent
import com.hssoft.counries.di.module.ApplicationModule
import timber.log.Timber

class AndroidApplication : Application() {
    val appComponent: ApplicationComponent by lazy {
        return@lazy DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        setupTimber()
    }

    private fun setupTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}