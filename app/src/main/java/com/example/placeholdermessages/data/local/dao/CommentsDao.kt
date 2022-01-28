package com.example.placeholdermessages.data.local.dao

import androidx.room.*
import com.example.placeholdermessages.data.local.model.CommentEntity

@Dao
interface CommentsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(comment: CommentEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(comment: List<CommentEntity>): List<Long>

    @Query("SELECT * FROM comments;")
    fun getAll(): List<CommentEntity>

    @Query("DELETE FROM comments")
    fun drop()

    @Update
    fun update(comment: CommentEntity)

    @Delete
    fun delete(comment: CommentEntity)
}
