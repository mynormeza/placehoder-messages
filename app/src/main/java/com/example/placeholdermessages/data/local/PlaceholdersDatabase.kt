package com.example.placeholdermessages.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.placeholdermessages.data.local.dao.CommentsDao
import com.example.placeholdermessages.data.local.dao.PostsDao
import com.example.placeholdermessages.data.local.dao.UsersDao
import com.example.placeholdermessages.data.local.model.CommentEntity
import com.example.placeholdermessages.data.local.model.PostEntity
import com.example.placeholdermessages.data.local.model.UserEntity

@Database(
    entities = [
        PostEntity::class,
        UserEntity::class,
        CommentEntity::class,
    ],
    version = 1,
)
abstract class PlaceholdersDatabase : RoomDatabase() {
    abstract fun postsDao(): PostsDao
    abstract fun usersDao(): UsersDao
    abstract fun commentsDao(): CommentsDao
}
