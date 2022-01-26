package com.example.placeholdermessages.data.remote.service

import com.example.placeholdermessages.data.remote.model.PostItem
import retrofit2.Call
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PostsService @Inject constructor(retrofit: Retrofit) : PostsApi {
    private val postsApi: PostsApi by lazy { retrofit.create(PostsApi::class.java) }

    override fun getPosts(): Call<List<PostItem>> {
        return postsApi.getPosts()

    }
}