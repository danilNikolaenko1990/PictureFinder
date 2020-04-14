package com.daniln.picturefinder.di

import com.daniln.picturefinder.network.ImagesFetcher
import com.daniln.picturefinder.presenters.ImagePresenter
import com.daniln.picturefinder.presenters.UserPresenter
import dagger.Module
import dagger.Provides

@Module
class PresenterModule {
    @Provides
    fun provideImagePresenter(imagesFetcher: ImagesFetcher): ImagePresenter = ImagePresenter(imagesFetcher)

    @Provides
    fun provideUserPresenter(): UserPresenter = UserPresenter()
}