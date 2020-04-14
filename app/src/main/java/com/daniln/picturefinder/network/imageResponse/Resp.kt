package com.daniln.picturefinder.network.imageResponse

data class Resp(
    val results: List<Result>,
    val total: Int,
    val total_pages: Int
)