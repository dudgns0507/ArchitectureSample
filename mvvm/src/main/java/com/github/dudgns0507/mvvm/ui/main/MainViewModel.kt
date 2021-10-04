package com.github.dudgns0507.mvvm.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.github.dudgns0507.core.base.BaseViewModel
import com.github.dudgns0507.core.util.network.Resource
import com.github.dudgns0507.domain.usecase.JsonUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    state: SavedStateHandle,
    private val jsonUseCases: JsonUseCases
) : BaseViewModel(state) {
    private val _state = MutableLiveData(PostsState())
    val state: LiveData<PostsState> = _state

    init {
        getPosts(0, 10)
    }

    fun onEvent(event: PostsEvent) {
        when(event) {
            is PostsEvent.Read -> {
                if(event.start == state.value?.start &&
                    event.limit == state.value?.limit) {
                    return
                }
                getPosts(event.start, event.limit)
            }
            is PostsEvent.Patch -> viewModelScope.launch {
                jsonUseCases.patchPostUseCase(event.postId, event.post)
            }
            is PostsEvent.Delete -> viewModelScope.launch {
                jsonUseCases.deletePostUseCase(event.postId)
            }
            is PostsEvent.ReadFirst -> {
                state.value?.let {
                    _state.value = it.copy(
                        posts = emptyList(),
                        start = 0
                    )
                    getPosts(it.start, it.limit)
                }
            }
            is PostsEvent.ReadMore -> {
                state.value?.let {
                    _state.value = _state.value?.copy(
                        start = it.start.plus(1)
                    )
                    getPosts(it.start, it.limit)
                }
            }
        }
    }

    private fun getPosts(start: Int, limit: Int) {
        viewModelScope.launch {
            jsonUseCases.getPostsUseCase(start, limit)
                .onStart {
                    // Loading
                }
                .catch { e ->
                    // Hide Loading
                    // Error Handling
                }
                .collect { result ->
                    // Hide Loading
                    // Result Handling
                    when(result) {
                        is Resource.Success -> {
                            state.value?.let {
                                _state.value = it.copy(
                                    posts = it.posts + result.data
                                )
                            }
                        }
                        is Resource.Failure -> TODO()
                        is Resource.Loading -> TODO()
                    }
                }
        }
    }
}