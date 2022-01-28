package com.example.placeholdermessages.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.placeholdermessages.domain.model.User

@Entity(tableName = "users")
data class UserEntity(
    @ColumnInfo(name = "user_id") @PrimaryKey val userId: Long,
    @ColumnInfo(name = "username") val username: String,
    @ColumnInfo(name = "user_phone") val phone: String,
    @ColumnInfo(name = "user_email") val email: String,
    @ColumnInfo(name = "user_website") val website: String,
) {
    fun toUser() = User(
        userId,
        username,
        phone,
        email,
        website
    )
}
