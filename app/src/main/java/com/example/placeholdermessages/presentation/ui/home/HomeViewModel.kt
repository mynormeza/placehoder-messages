package com.example.placeholdermessages.presentation.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.placeholdermessages.data.local.DatabaseManager
import com.example.placeholdermessages.domain.interactors.post.DeletePostUsaCase
import com.example.placeholdermessages.domain.interactors.post.LoadPostsUseCase
import com.example.placeholdermessages.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val loadPostsUseCase: LoadPostsUseCase,
    private val deletePost: DeletePostUsaCase,
) : BaseViewModel() {
    private val _loadSuccessful = MutableLiveData(false)
    val loadSuccessful: LiveData<Boolean> = _loadSuccessful

    fun loadPosts(loadOnDemand: Boolean = false) {
        loadPostsUseCase(loadOnDemand, viewModelScope) {
            it.fold(::handleFailure) {
                _loadSuccessful.value = true
            }
        }
    }

    fun deleteAllPosts() {
        deletePost(DatabaseManager.DELETE_ALL_POSTS, viewModelScope) {
            it.fold(::handleFailure) {}
        }
    }
}
