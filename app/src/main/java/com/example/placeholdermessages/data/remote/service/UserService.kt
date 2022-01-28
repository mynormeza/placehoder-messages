package com.example.placeholdermessages.data.remote.service

import com.example.placeholdermessages.data.remote.model.UserItem
import retrofit2.Call
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserService @Inject constructor(retrofit: Retrofit) : UserApi {
    private val userApi: UserApi by lazy { retrofit.create(UserApi::class.java) }

    override fun getUsers(): Call<List<UserItem>> {
        return userApi.getUsers()
    }
}
