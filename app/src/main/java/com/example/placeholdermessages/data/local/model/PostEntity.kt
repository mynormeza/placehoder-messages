package com.example.placeholdermessages.data.local.model

import androidx.room.*
import androidx.room.ForeignKey.CASCADE
import com.example.placeholdermessages.domain.model.Post

@Entity(
    tableName = "posts",
    foreignKeys = [
        ForeignKey(
            entity = UserEntity::class,
            parentColumns = ["user_id"],
            childColumns = ["post_user_id"],
            onDelete = CASCADE
        ),

    ],

    indices = [
        Index("post_user_id"),
    ]
)
data class PostEntity(
    @ColumnInfo(name = "post_id") @PrimaryKey val id: Long,
    @ColumnInfo(name = "post_title") val title: String,
    @ColumnInfo(name = "post_body") val body: String,
    @ColumnInfo(name = "post_is_read") val isRead: Boolean,
    @ColumnInfo(name = "post_is_favorite") var isFavorite: Boolean,
    @ColumnInfo(name = "post_user_id") val userId: Long,
) {
    fun toPost(): Post = Post(
        id,
        title,
        body,
        isRead,
        isFavorite,
        userId,
    )

    fun toPartial() = PartialPost(
        id,
        title,
        body,
        userId
    )
}

data class PartialPost(
    @ColumnInfo(name = "post_id") @PrimaryKey val id: Long,
    @ColumnInfo(name = "post_title") val title: String,
    @ColumnInfo(name = "post_body") val body: String,
    @ColumnInfo(name = "post_user_id") val userId: Long,
)
