package com.daniln.picturefinder.domain

data class Image(
    val thumbUrl: String,
    val altDescription: String,
    val likes: Int,
    val location: String,
    val userName: String
)