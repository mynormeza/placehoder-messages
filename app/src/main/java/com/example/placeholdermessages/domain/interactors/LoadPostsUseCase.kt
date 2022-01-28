package com.example.placeholdermessages.domain.interactors

import arrow.core.Either
import arrow.core.None
import com.example.placeholdermessages.core.Failure
import com.example.placeholdermessages.domain.repositories.IPostRepository
import javax.inject.Inject

class LoadPostsUseCase @Inject constructor(private val postRepository: IPostRepository) : UseCase<None, Boolean>() {
    override suspend fun run(params: Boolean): Either<Failure, None> {
        return postRepository.loadPosts(params)
    }
}
