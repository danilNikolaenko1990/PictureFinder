package com.daniln.picturefinder.network

import com.daniln.picturefinder.domain.ImageGalleryItem
import com.daniln.picturefinder.domain.ImageRepository
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ImagesFetcher constructor(private val client: UnsplashService) : ImageRepository {
    override suspend fun getImages(query: String, page: Int, perPage: Int): List<ImageGalleryItem> {
        val result = client.searchPhotos(query, page, perPage)
        val imagesToReturn: ArrayList<ImageGalleryItem> = ArrayList()

        for (imageItem in result.results) {
            imagesToReturn.add(
                ImageGalleryItem(
                    imageItem.urls.thumb,
                    imageItem.alt_description,
                    imageItem.likes,
                    imageItem.user?.location,
                    imageItem.user?.username
                )
            )
        }

        return imagesToReturn
    }
}

class UnsplashServiceFactory constructor(private val interceptors: List<Interceptor>) {
    fun create(): UnsplashService {
        val builder = OkHttpClient.Builder()
        for (interceptor in interceptors) {
            builder.addInterceptor(interceptor)
        }
        val client = builder.build()

        return Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(UnsplashService::class.java)
    }

    companion object {
        private const val BASE_URL = "https://api.unsplash.com"
    }
}