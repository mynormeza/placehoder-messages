package com.example.placeholdermessages.data.remote.model

import com.example.placeholdermessages.data.local.model.PostEntity
import com.example.placeholdermessages.domain.model.Post

data class PostItem(
    val id: Long,
    val title: String,
    val body: String,
    val userId: Long,
) {
    fun toPost(): Post = Post(
        id,
        title,
        body,
        isRead = true,
        isFavorite = false,
        userId,
    )

    fun toEntity(isRead: Boolean = false): PostEntity = PostEntity(
        id,
        title,
        body,
        isRead,
        isFavorite = false,
        userId,
    )
}
