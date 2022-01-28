package com.example.placeholdermessages.data.remote.service

import com.example.placeholdermessages.data.remote.model.CommentItem
import retrofit2.Call
import retrofit2.http.GET

interface CommentsApi {
    @GET("/comments")
    fun getComments(): Call<List<CommentItem>>
}
