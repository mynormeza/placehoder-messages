package com.example.placeholdermessages.domain.repositories

import arrow.core.Either
import com.example.placeholdermessages.core.Failure
import com.example.placeholdermessages.domain.model.Post

interface IPostRepository {
    fun getPosts(): Either<Failure, List<Post>>
}