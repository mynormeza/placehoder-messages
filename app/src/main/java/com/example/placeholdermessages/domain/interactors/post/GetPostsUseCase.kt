package com.example.placeholdermessages.domain.interactors.post

import arrow.core.Either
import com.example.placeholdermessages.core.Failure
import com.example.placeholdermessages.domain.model.Post
import com.example.placeholdermessages.domain.repositories.IPostRepository
import com.example.placeholdermessages.presentation.ui.home.adapter.FilterPosts
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPostsUseCase @Inject constructor(private val postRepository: IPostRepository) {
    fun getFlow(filterPosts: FilterPosts): Either<Failure, Flow<List<Post>>> {
        return postRepository.getPosts(filterPosts)
    }
}
