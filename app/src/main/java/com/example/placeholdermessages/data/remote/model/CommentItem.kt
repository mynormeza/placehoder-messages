package com.example.placeholdermessages.data.remote.model

import com.example.placeholdermessages.data.local.model.CommentEntity

data class CommentItem(
    val id: Long,
    val postId: Long,
    val body: String,
) {
    fun toEntity() = CommentEntity(
        id,
        postId,
        body,
    )
}
