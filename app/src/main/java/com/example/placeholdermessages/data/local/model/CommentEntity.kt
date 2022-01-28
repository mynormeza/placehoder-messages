package com.example.placeholdermessages.data.local.model

import androidx.room.*

@Entity(
    tableName = "comments",
    foreignKeys = [
        ForeignKey(
            entity = PostEntity::class,
            parentColumns = ["post_id"],
            childColumns = ["comment_post_id"],
            onDelete = ForeignKey.CASCADE
        ),

    ],
    indices = [
        Index("comment_post_id"),
    ],
)
data class CommentEntity(
    @ColumnInfo(name = "comment_id") @PrimaryKey val id: Long,
    @ColumnInfo(name = "comment_post_id") val postId: Long,
    @ColumnInfo(name = "comment_body") val body: String,
)
