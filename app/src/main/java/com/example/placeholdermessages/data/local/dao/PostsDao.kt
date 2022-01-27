package com.example.placeholdermessages.data.local.dao

import androidx.room.*
import com.example.placeholdermessages.data.local.model.PostEntity

@Dao
interface PostsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(post: PostEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(posts: List<PostEntity>)


    @Query("SELECT * FROM posts " + "WHERE posts.post_id = :id")
    fun get(id: Long): PostEntity

    @Query("SELECT * FROM posts;")
    fun getAll(): List<PostEntity>

    @Update
    fun update(post: PostEntity)

    @Delete
    fun delete(post: PostEntity)
}