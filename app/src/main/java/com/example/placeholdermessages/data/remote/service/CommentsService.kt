package com.example.placeholdermessages.data.remote.service

import com.example.placeholdermessages.data.remote.model.CommentItem
import retrofit2.Call
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CommentsService @Inject constructor(retrofit: Retrofit) : CommentsApi {
    private val commentsApi: CommentsApi by lazy { retrofit.create(CommentsApi::class.java) }

    override fun getComments(): Call<List<CommentItem>> {
        return commentsApi.getComments()
    }
}
