package com.example.placeholdermessages.data.remote.service

import com.example.placeholdermessages.data.remote.model.PostItem
import retrofit2.Call
import retrofit2.http.GET

interface PostsApi {
    @GET("/posts")
    fun getPosts(): Call<List<PostItem>>
}