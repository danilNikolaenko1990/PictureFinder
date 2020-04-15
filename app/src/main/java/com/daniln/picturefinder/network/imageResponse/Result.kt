package com.daniln.picturefinder.network.imageResponse

data class Result(
    val alt_description: String?,
    val categories: List<Any>?,
    val color: String?,
    val created_at: String?,
    val current_user_collections: List<Any>?,
    val description: Any?,
    val height: Int?,
    val id: String?,
    val liked_by_user: Boolean?,
    val likes: Int?,
    val links: Links?,
    val promoted_at: Any?,
    val tags: List<Tag>?,
    val updated_at: String?,
    val urls: UrlsX,
    val user: UserX?,
    val width: Int?
)