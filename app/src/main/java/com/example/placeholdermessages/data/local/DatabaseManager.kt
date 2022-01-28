package com.example.placeholdermessages.data.local

import com.example.placeholdermessages.data.local.model.*
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
    fun getPostsFlow(filterPosts: FilterPosts): Flow<List<PostEntity>> =
        if (filterPosts == FilterPosts.All) {
            appDatabase.postsDao().getPostsFlow()
        } else {
            appDatabase.postsDao().getFavoritePostsFlow()
        }
// TODO test
    fun getSinglePost(id: Long): PostDetailsWithComments {
        return appDatabase.postsDao().readPost(id)
    }

    fun toggleFavorite(id: Long, isFavorite: Boolean) {
        val data = if (isFavorite) {
            1
        } else {
            0
        }
        appDatabase.postsDao().updateFavorite(id, data)
    }

    fun deletePost(id: Long) {
        appDatabase.postsDao().deleteById(id)
    }

    fun dropPosts() {
        appDatabase.postsDao().drop()
    }

    fun saveUsers(users: List<UserEntity>) = appDatabase.usersDao().insertAll(users)
    fun saveComments(comments: List<CommentEntity>) = appDatabase.commentsDao().insertAll(comments)
}
