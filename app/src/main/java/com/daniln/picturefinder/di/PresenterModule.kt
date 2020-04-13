package com.daniln.picturefinder.di

import android.content.Context
import com.daniln.picturefinder.network.ImagesRepo
import com.daniln.picturefinder.presenters.ImagePresenter
import com.daniln.picturefinder.presenters.UserPresenter
import com.readystatesoftware.chuck.ChuckInterceptor
import dagger.Module
import dagger.Provides

@Module
class PresenterModule {
    @Provides
    fun provideImagePresenter(images: ImagesRepo): ImagePresenter = ImagePresenter(images)

    @Provides
    fun provideUserPresenter(): UserPresenter = UserPresenter()

    @Provides
    fun provideRepo(chuck: ChuckInterceptor) = ImagesRepo(chuck)

    @Provides
    fun provideChuck(context: Context) = ChuckInterceptor(context)
}