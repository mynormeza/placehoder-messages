package com.example.placeholdermessages.data.local.dao

import androidx.room.*
import com.example.placeholdermessages.data.local.model.UserEntity

@Dao
interface UsersDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: UserEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(users: List<UserEntity>): List<Long>

    @Query("SELECT * FROM users;")
    fun getAll(): List<UserEntity>

    @Query("DELETE FROM users")
    fun drop()

    @Update
    fun update(user: UserEntity)

    @Delete
    fun delete(user: UserEntity)
}
