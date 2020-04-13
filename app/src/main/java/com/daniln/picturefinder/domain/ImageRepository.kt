package com.daniln.picturefinder.domain

interface ImageRepository {
    fun searchImages(text: String, pageNumber: Int)
}