package com.example.placeholdermessages.domain.model

data class Comment(
    val id: Long,
    val postId: Long,
    val body: String,
)
