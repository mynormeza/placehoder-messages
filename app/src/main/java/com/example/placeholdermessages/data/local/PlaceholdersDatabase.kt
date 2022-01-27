package com.example.placeholdermessages.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.placeholdermessages.data.local.dao.PostsDao
import com.example.placeholdermessages.data.local.model.PostEntity

@Database(
    entities = [
        PostEntity::class,
    ],
    version = 1,
)
abstract class PlaceholdersDatabase : RoomDatabase() {
    abstract fun postsDao(): PostsDao
}
