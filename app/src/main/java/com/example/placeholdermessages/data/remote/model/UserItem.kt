package com.example.placeholdermessages.data.remote.model

import com.example.placeholdermessages.data.local.model.UserEntity
import com.google.gson.annotations.SerializedName

data class UserItem(
    @SerializedName("id") val userId: Long,
    val username: String,
    val phone: String,
    val email: String,
    val website: String,
) {
    fun toUserEntity() = UserEntity(
        userId,
        username,
        phone,
        email,
        website
    )
}
