package com.example.placeholdermessages.presentation.ui.postdetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.placeholdermessages.domain.interactors.post.GetSinglePostUseCase
import com.example.placeholdermessages.domain.interactors.post.TogglePostFavoriteUseCase
import com.example.placeholdermessages.domain.model.Post
import com.example.placeholdermessages.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PostDetailsViewModel @Inject constructor (
    private val getSinglePost: GetSinglePostUseCase,
    private val togglePostFavorite: TogglePostFavoriteUseCase
) : BaseViewModel() {
    private val _post = MutableLiveData<Post>()
    val post get() = _post

    fun getPost(id: Long) {
        getSinglePost(id, viewModelScope) {
            it.fold(::handleFailure) { p ->
                _post.value = p
            }
        }
    }

    fun toggle() {
        _post.value?.let { p ->
            p.isFavorite = !p.isFavorite
            togglePostFavorite(p, viewModelScope) {
                it.fold(::handleFailure) {
                    _post.value = p
                }
            }
        }
    }
}
