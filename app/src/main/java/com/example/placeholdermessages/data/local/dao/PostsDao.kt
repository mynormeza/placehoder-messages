package com.example.placeholdermessages.data.local.dao

import androidx.room.*
import com.example.placeholdermessages.data.local.model.PartialPost
import com.example.placeholdermessages.data.local.model.PostEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PostsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(post: PostEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(posts: List<PostEntity>): List<Long>


    @Query("SELECT * FROM posts WHERE posts.post_id = :id")
    fun get(id: Long): PostEntity

    @Query("SELECT * FROM posts;")
    fun getAll(): List<PostEntity>

    @Query("SELECT * FROM posts")
    fun getPostsFlow(): Flow<List<PostEntity>>

    @Query("SELECT * FROM posts WHERE post_is_favorite = 1;")
    fun getFavoritePostsFlow(): Flow<List<PostEntity>>

    @Query("DELETE FROM posts WHERE posts.post_id = :id")
    fun deleteById(id: Long)

    @Query("DELETE FROM posts")
    fun drop()

    @Update(entity = PostEntity::class)
    fun updateAll(drills: List<PartialPost>)

    @Update
    fun update(post: PostEntity)

    @Delete
    fun delete(post: PostEntity)

    @Transaction
    fun upsertAll(posts: List<PostEntity>) {
        val insertResult = insertAll(posts)
        val updateList = mutableListOf<PostEntity>()

        insertResult.forEachIndexed { index, result ->
            if (result == (-1).toLong()) {
                updateList.add(posts[index])
            }
        }

        if (updateList.isNotEmpty()) {
            updateAll(updateList.map { it.toPartial() })
        }
    }
}