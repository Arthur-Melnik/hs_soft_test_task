package com.hssoft.counries

import android.app.Application
import com.hssoft.counries.di.DaggerApplicationComponent

class AndroidApplication : Application() {
    val appComponent = DaggerApplicationComponent.create()
}