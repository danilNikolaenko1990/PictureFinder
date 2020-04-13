package com.daniln.picturefinder.network

import android.util.AndroidRuntimeException
import com.readystatesoftware.chuck.ChuckInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class ImagesRepo (private val chuck: ChuckInterceptor) {
    suspend fun getImages(query: String, page: Int, perPage: Int): Result {
        val client = OkHttpClient.Builder()
            .addInterceptor(chuck)
            .build()

        val retrofitPosts = Retrofit
            .Builder()
            .baseUrl("https://api.unsplash.com")
            .addConverterFactory(MoshiConverterFactory.create())
            .client(client)
            .build()
            .create(Images::class.java)
        var result: List<ImageModel>? = null

        try {
            result = retrofitPosts
                .getImagesAsync(query, page, perPage)
        } catch (e: AndroidRuntimeException) {

        }


        return Result(result)
    }

    data class Result(val posts: List<ImageModel>?)
}