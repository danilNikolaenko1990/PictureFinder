package com.daniln.picturefinder.network

import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Images {
    @GET("/search/users")
    suspend fun getImagesAsync(
        @Query("query") method: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): List<ImageModel>
}