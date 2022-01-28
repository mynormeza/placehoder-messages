package com.example.placeholdermessages.data.local.model

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import androidx.room.Relation

data class PostDetailsWithComments(
    @ColumnInfo(name = "post_id") val postId: Long,
    @ColumnInfo(name = "post_body") val body: String,
    @ColumnInfo(name = "post_is_favorite") var isFavorite: Boolean,
    @ColumnInfo(name = "user_id") @PrimaryKey val userId: Long,
    @ColumnInfo(name = "username") val username: String,
    @ColumnInfo(name = "user_phone") val phone: String,
    @ColumnInfo(name = "user_email") val email: String,
    @ColumnInfo(name = "user_website") val website: String,
    @Relation(
        parentColumn = "post_id",
        entityColumn = "comment_post_id"
    )
    var comments: List<CommentEntity>,
)
