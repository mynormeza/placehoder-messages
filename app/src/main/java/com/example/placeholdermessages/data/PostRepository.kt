package com.example.placeholdermessages.data

import arrow.core.Either
import arrow.core.None
import com.example.placeholdermessages.core.Failure
import com.example.placeholdermessages.core.NetworkHandler
import com.example.placeholdermessages.data.local.DatabaseManager
import com.example.placeholdermessages.data.local.mapper.CacheMapper
import com.example.placeholdermessages.data.local.model.PostEntity
import com.example.placeholdermessages.data.remote.service.PostsService
import com.example.placeholdermessages.domain.model.Post
import com.example.placeholdermessages.domain.repositories.IPostRepository
import com.example.placeholdermessages.presentation.ui.home.adapter.FilterPosts
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import retrofit2.Call
import javax.inject.Inject

class PostRepository @Inject constructor(
    private val networkHandler: NetworkHandler,
    private val postsService: PostsService,
    private val databaseManager: DatabaseManager,
    private val cacheMapper: CacheMapper<Post, PostEntity>
) : IPostRepository {
    override fun getPosts(filter: FilterPosts): Either<Failure, Flow<List<Post>>> {
        return try {
            val cachedPosts = databaseManager.getPostsFlow(filter)
            Either.Right(cachedPosts.map { it.map { p -> p.toPost() } })
        } catch (exception: Throwable) {
            Either.Left(Failure.CacheError)
        }
    }

    override fun loadPosts(loadOnDemand: Boolean): Either<Failure, None> {
        val cachedPosts = databaseManager.getAllPosts()
        if (cachedPosts.isEmpty() || loadOnDemand) {
            return when (networkHandler.isNetworkAvailable()) {
                true -> request(
                    postsService.getPosts(), {
                        databaseManager.savePosts(
                            it.mapIndexed { index, postItem ->
                                if (index <= 19) {
                                    postItem.toEntity()
                                } else {
                                    postItem.toEntity(true)
                                }
                            },
                        )
                        None
                    },
                    emptyList()
                )
                else -> Either.Left(Failure.NetworkConnection)
            }
        } else {
            return Either.Right(None)
        }
    }

    override fun deletePost(id: Long): Either<Failure, None> {
        return try {
            if (id == DatabaseManager.DELETE_ALL_POSTS) {
                databaseManager.dropPosts()
            } else {
                databaseManager.deletePost(id)
            }
            Either.Right(None)
        } catch (exception: Throwable) {
            Either.Left(Failure.CacheError)
        }
    }

    override fun getSinglePost(id: Long): Either<Failure, Post> {
        return try {
            Either.Right(databaseManager.getSinglePost(id).toPost())
        } catch (exception: Throwable) {
            Either.Left(Failure.CacheError)
        }
    }

    override fun toggleFavorite(post: Post): Either<Failure, None> {
        return try {
            databaseManager.toggleFavorite(cacheMapper.mapToCache(post))
            Either.Right(None)
        } catch (exception: Throwable) {
            Either.Left(Failure.CacheError)
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
