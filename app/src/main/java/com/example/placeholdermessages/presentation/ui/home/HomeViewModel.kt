package com.example.placeholdermessages.presentation.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import arrow.core.None
import com.example.placeholdermessages.domain.interactors.LoadPostsUseCase
import com.example.placeholdermessages.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val loadPostsUseCase: LoadPostsUseCase
) : BaseViewModel() {
    private val _loadSuccessful = MutableLiveData(false)
    val loadSuccessful: LiveData<Boolean> = _loadSuccessful

    fun loadPosts() {
        loadPostsUseCase(None, viewModelScope) {
            it.fold(::handleFailure) {
                _loadSuccessful.value = true
            }
        }
    }
}