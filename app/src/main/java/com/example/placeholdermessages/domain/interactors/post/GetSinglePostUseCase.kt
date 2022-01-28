package com.example.placeholdermessages.domain.interactors.post

import arrow.core.Either
import com.example.placeholdermessages.core.Failure
import com.example.placeholdermessages.data.local.model.PostDetailsWithComments
import com.example.placeholdermessages.domain.interactors.UseCase
import com.example.placeholdermessages.domain.repositories.IPostRepository
import javax.inject.Inject

class GetSinglePostUseCase @Inject constructor(private val postsRepository: IPostRepository) : UseCase<PostDetailsWithComments, Long>() {
    override suspend fun run(params: Long): Either<Failure, PostDetailsWithComments> {
        return postsRepository.getSinglePost(params)
    }
}
