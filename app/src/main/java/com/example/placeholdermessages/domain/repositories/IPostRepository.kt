package com.example.placeholdermessages.domain.repositories

import arrow.core.Either
import arrow.core.None
import com.example.placeholdermessages.core.Failure
import com.example.placeholdermessages.domain.model.Post
import com.example.placeholdermessages.presentation.ui.home.adapter.FilterPosts
import kotlinx.coroutines.flow.Flow

interface IPostRepository {
    fun getPosts(filter: FilterPosts): Either<Failure, Flow<List<Post>>>
    fun getSinglePost(id: Long): Either<Failure, Post>
    fun toggleFavorite(post: Post): Either<Failure, None>
    fun loadPosts(loadOnDemand: Boolean): Either<Failure, None>
    fun deletePost(id: Long): Either<Failure, None>
}
