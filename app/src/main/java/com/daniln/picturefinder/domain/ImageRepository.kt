package com.daniln.picturefinder.domain

interface ImageRepository {
    suspend fun getImages(query: String, page: Int, perPage: Int): List<Image>
}