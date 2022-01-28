package com.example.placeholdermessages.domain.interactors.post

import arrow.core.Either
import com.example.placeholdermessages.core.Failure
import com.example.placeholdermessages.domain.interactors.UseCase
import com.example.placeholdermessages.domain.model.Post
import com.example.placeholdermessages.domain.repositories.IPostRepository
import javax.inject.Inject

class GetSinglePostUseCase @Inject constructor(private val postsRepository: IPostRepository) : UseCase<Post, Long>() {
    override suspend fun run(params: Long): Either<Failure, Post> {
        return postsRepository.getSinglePost(params)
    }
}
