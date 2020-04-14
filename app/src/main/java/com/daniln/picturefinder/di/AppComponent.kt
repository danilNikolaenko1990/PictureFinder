package com.daniln.picturefinder.di

import android.content.Context
import com.daniln.picturefinder.ui.fragments.ImagesFragment
import com.daniln.picturefinder.ui.fragments.UsersFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(modules = [PresenterModule::class, NetworkModule::class])
@Singleton
interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun inject(fragment: UsersFragment)
    fun inject(activity: ImagesFragment)
}