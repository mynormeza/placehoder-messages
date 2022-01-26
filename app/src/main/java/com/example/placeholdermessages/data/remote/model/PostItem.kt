package com.example.placeholdermessages.data.remote.model

import com.example.placeholdermessages.domain.model.Post

data class PostItem(
    val id: Long,
    val title: String,
    val body: String,
) {
    fun toPost(): Post = Post(
        id,
        title,
        body
    )
}