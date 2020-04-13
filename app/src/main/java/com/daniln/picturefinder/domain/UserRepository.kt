package com.daniln.picturefinder.domain

interface UserRepository {
    fun search(text: String)
}