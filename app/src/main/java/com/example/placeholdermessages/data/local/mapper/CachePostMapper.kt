package com.example.placeholdermessages.data.local.mapper

import com.example.placeholdermessages.data.local.model.PostEntity
import com.example.placeholdermessages.domain.model.Post
import javax.inject.Inject

class CachePostMapper @Inject constructor() : CacheMapper<Post, PostEntity> {
    override fun mapToCache(type: Post): PostEntity {
        return PostEntity(
            type.id, type.title, type.body, type.isRead,
            type.isFavorite,
        )
    }
}
