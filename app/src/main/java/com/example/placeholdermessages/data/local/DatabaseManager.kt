package com.example.placeholdermessages.data.local

import com.example.placeholdermessages.data.local.model.PostEntity
import com.example.placeholdermessages.presentation.ui.home.adapter.FilterPosts
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DatabaseManager @Inject constructor(private val appDatabase: PlaceholdersDatabase) {
    companion object {
        const val DELETE_ALL_POSTS: Long = -1110
    }

    fun savePosts(post: List<PostEntity>) {
        appDatabase.postsDao().insertAll(post)
    }

    fun getAllPosts(): List<PostEntity> = appDatabase.postsDao().getAll()
    fun getPostsFlow(filterPosts: FilterPosts): Flow<List<PostEntity>> = if (filterPosts == FilterPosts.All) {
        appDatabase.postsDao().getPostsFlow()
    } else {
        appDatabase.postsDao().getFavoritePostsFlow()
    }

    fun deletePost(id: Long) {
        appDatabase.postsDao().deleteById(id)
    }

    fun dropPosts() {
        appDatabase.postsDao().drop()
    }
}
