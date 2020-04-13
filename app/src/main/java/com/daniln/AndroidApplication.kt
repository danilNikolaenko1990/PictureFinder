package com.daniln

import android.app.Application
import com.daniln.picturefinder.di.AppComponent
import com.daniln.picturefinder.di.DaggerAppComponent

class AndroidApplication : Application() {
    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(applicationContext)
    }
}
