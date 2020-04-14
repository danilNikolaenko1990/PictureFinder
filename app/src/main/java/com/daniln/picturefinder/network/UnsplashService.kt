package com.daniln.picturefinder.network

import com.daniln.picturefinder.network.imageResponse.Resp
import retrofit2.http.GET
import retrofit2.http.Query

interface UnsplashService {
    @GET("/search/photos")
    suspend fun searchPhotos(
        @Query("query") method: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): Resp
}