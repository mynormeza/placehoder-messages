package com.example.placeholdermessages.data.remote.service

import com.example.placeholdermessages.data.remote.model.UserItem
import retrofit2.Call
import retrofit2.http.GET

interface UserApi {
    @GET("/users")
    fun getUsers(): Call<List<UserItem>>
}
