package com.example.placeholdermessages.domain.repositories

import arrow.core.Either
import arrow.core.None
import com.example.placeholdermessages.core.Failure
import com.example.placeholdermessages.data.local.model.PostDetailsWithComments
import com.example.placeholdermessages.domain.model.Post
import com.example.placeholdermessages.presentation.ui.home.adapter.FilterPosts
import kotlinx.coroutines.flow.Flow

interface IPostRepository {
    fun getPosts(filter: FilterPosts): Either<Failure, Flow<List<Post>>>
    // TODO test
    fun getSinglePost(id: Long): Either<Failure, PostDetailsWithComments>
    fun toggleFavorite(params: Pair<Long, Boolean>): Either<Failure, None>
    fun loadPosts(loadOnDemand: Boolean): Either<Failure, None>
    fun deletePost(id: Long): Either<Failure, None>
}
