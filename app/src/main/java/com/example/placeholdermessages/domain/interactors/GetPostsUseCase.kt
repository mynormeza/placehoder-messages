package com.example.placeholdermessages.domain.interactors

import arrow.core.Either
import arrow.core.None
import com.example.placeholdermessages.core.Failure
import com.example.placeholdermessages.domain.model.Post
import com.example.placeholdermessages.domain.repositories.IPostRepository
import javax.inject.Inject

class GetPostsUseCase @Inject constructor(private val postRepository: IPostRepository) : UseCase<List<Post>, None>() {
    override suspend fun run(params: None): Either<Failure, List<Post>> {
        return postRepository.getPosts()
    }
}