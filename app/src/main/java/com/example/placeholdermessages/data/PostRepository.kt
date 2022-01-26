package com.example.placeholdermessages.data

import arrow.core.Either
import com.example.placeholdermessages.core.Failure
import com.example.placeholdermessages.core.NetworkHandler
import com.example.placeholdermessages.data.remote.service.PostsService
import com.example.placeholdermessages.domain.model.Post
import com.example.placeholdermessages.domain.repositories.IPostRepository
import retrofit2.Call
import javax.inject.Inject

class PostRepository @Inject constructor(
    private val networkHandler: NetworkHandler,
    private val postsService: PostsService,
) : IPostRepository {
    override fun getPosts(): Either<Failure, List<Post>> {
        return when(networkHandler.isNetworkAvailable()) {
            true -> request(
                postsService.getPosts(), {
                    it.map { p -> p.toPost() }
                },
                emptyList()
            )
            else -> Either.Left(Failure.NetworkConnection)
        }
    }

    private fun <T, R> request(
        call: Call<T>,
        transform: (T) -> R,
        default: T
    ): Either<Failure, R> {
        return try {
            val response = call.execute()
            when (response.isSuccessful) {
                true -> Either.Right(transform((response.body() ?: default)))
                false -> Either.Left(Failure.ServerError)
            }
        } catch (exception: Throwable) {
            Either.Left(Failure.ServerError)
        }
    }
}