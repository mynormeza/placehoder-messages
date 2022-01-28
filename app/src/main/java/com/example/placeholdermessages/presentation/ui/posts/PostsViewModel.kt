package com.example.placeholdermessages.presentation.ui.posts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.placeholdermessages.domain.interactors.post.DeletePostUsaCase
import com.example.placeholdermessages.domain.interactors.post.GetPostsUseCase
import com.example.placeholdermessages.domain.model.Post
import com.example.placeholdermessages.presentation.base.BaseViewModel
import com.example.placeholdermessages.presentation.ui.home.adapter.FilterPosts
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostsViewModel @Inject constructor(
    private val getPosts: GetPostsUseCase,
    private val deletePost: DeletePostUsaCase,
) : BaseViewModel() {
    private val _postsList: MutableLiveData<List<Post>> = MutableLiveData()
    val posts: LiveData<List<Post>> = _postsList
    fun getPosts(filterPosts: FilterPosts) {
        getPosts.getFlow(filterPosts).fold(::handleFailure) { res ->
            viewModelScope.launch {
                res.collectLatest {
                    _postsList.value = it
                }
            }
        }
    }

    fun deletePost(id: Long) {
        deletePost(id, viewModelScope) {
            it.fold(::handleFailure) {}
        }
    }
}
