package com.example.placeholdermessages.domain.model

data class Post(
    val id: Long,
    val title: String,
    val body: String,
    val isRead: Boolean,
    val isFavorite: Boolean,
)
