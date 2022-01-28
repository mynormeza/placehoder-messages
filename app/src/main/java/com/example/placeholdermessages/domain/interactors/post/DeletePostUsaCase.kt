package com.example.placeholdermessages.domain.interactors.post

import arrow.core.Either
import arrow.core.None
import com.example.placeholdermessages.core.Failure
import com.example.placeholdermessages.domain.interactors.UseCase
import com.example.placeholdermessages.domain.repositories.IPostRepository
import javax.inject.Inject

class DeletePostUsaCase @Inject constructor(private val postsRepository: IPostRepository) : UseCase<None, Long>() {
    override suspend fun run(params: Long): Either<Failure, None> {
        return postsRepository.deletePost(params)
    }
}
