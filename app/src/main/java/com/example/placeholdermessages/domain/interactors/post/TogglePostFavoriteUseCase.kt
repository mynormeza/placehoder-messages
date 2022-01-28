package com.example.placeholdermessages.domain.interactors.post

import arrow.core.Either
import arrow.core.None
import com.example.placeholdermessages.core.Failure
import com.example.placeholdermessages.domain.interactors.UseCase
import com.example.placeholdermessages.domain.repositories.IPostRepository
import javax.inject.Inject

class TogglePostFavoriteUseCase @Inject constructor(private val postsRepository: IPostRepository) : UseCase<None, Pair<Long, Boolean>>() {
    override suspend fun run(params: Pair<Long, Boolean>): Either<Failure, None> {
        return postsRepository.toggleFavorite(params)
    }
}
