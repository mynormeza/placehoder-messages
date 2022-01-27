package com.example.placeholdermessages.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.placeholdermessages.domain.model.Post

@Entity(tableName = "posts")
data class PostEntity (
    @ColumnInfo(name = "post_id") @PrimaryKey val id: Long,
    @ColumnInfo(name = "post_title") val title: String,
    @ColumnInfo(name = "post_body") val body: String,
    @ColumnInfo(name = "post_is_read") val isRead: Boolean = false,
) {
    fun toPost(): Post = Post(
        id,
        title,
        body
    )
}