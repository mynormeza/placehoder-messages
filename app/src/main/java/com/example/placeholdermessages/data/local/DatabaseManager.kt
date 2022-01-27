package com.example.placeholdermessages.data.local

import com.example.placeholdermessages.data.local.model.PostEntity
import javax.inject.Inject

class DatabaseManager @Inject constructor(private val appDatabase: PlaceholdersDatabase) {
    fun savePosts(post: List<PostEntity>) {
        appDatabase.postsDao().insertAll(post)
    }

    fun getAllPosts(): List<PostEntity> = appDatabase.postsDao().getAll()

}
