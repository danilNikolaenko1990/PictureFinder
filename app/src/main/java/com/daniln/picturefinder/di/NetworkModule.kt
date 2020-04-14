package com.daniln.picturefinder.di

import android.content.Context
import com.daniln.picturefinder.network.ImagesFetcher
import com.daniln.picturefinder.network.TokenInterceptor
import com.daniln.picturefinder.network.UnsplashService
import com.daniln.picturefinder.network.UnsplashServiceFactory
import com.readystatesoftware.chuck.ChuckInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor

@Module
class NetworkModule {
    @Provides
    fun provideUnsplashService(
        tokenInterceptor: TokenInterceptor,
        debugInterceptor: ChuckInterceptor
    ): UnsplashService {
        val interceptors: ArrayList<Interceptor> = ArrayList()

        interceptors.add(debugInterceptor)
        interceptors.add(tokenInterceptor)

        val factory = UnsplashServiceFactory(interceptors)
        return factory.create()

    }

    @Provides
    fun provideDebugInterceptor(context: Context): ChuckInterceptor {
        return ChuckInterceptor(context)
    }

    @Provides
    fun provideTokenInterceptor() = TokenInterceptor()

    @Provides
    fun provideImagesFetcher(unsplashService: UnsplashService): ImagesFetcher {
        return ImagesFetcher(unsplashService)
    }
}