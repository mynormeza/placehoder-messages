package com.example.placeholdermessages.presentation.ui.posts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import arrow.core.None
import com.example.placeholdermessages.domain.interactors.GetPostsUseCase
import com.example.placeholdermessages.domain.model.Post
import com.example.placeholdermessages.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PostsViewModel @Inject constructor(
    private val getPosts: GetPostsUseCase
) : BaseViewModel() {
    private val _postsList: MutableLiveData<List<Post>> = MutableLiveData()
    val posts: LiveData<List<Post>> = _postsList

    fun getPosts() {
        getPosts(None, viewModelScope) {
            it.fold(::handleFailure) { res ->
                _postsList.value = res
            }
        }
    }
}