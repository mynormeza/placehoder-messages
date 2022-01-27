package com.example.placeholdermessages.domain.repositories

import arrow.core.Either
import arrow.core.None
import com.example.placeholdermessages.core.Failure
import com.example.placeholdermessages.domain.model.Post

interface IPostRepository {
    fun getPosts(): Either<Failure, List<Post>>
    fun loadPosts(): Either<Failure, None>
}