package com.github.dudgns0507.mvvm.ui.main

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.github.dudgns0507.core.base.BaseViewModel
import com.github.dudgns0507.domain.usecase.JsonUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    state: SavedStateHandle,
    private val jsonUseCases: JsonUseCases
) : BaseViewModel(state) {
    private val _state = MutableLiveData(PostsState())
    val state: LiveData<PostsState> = _state

    private var getPostsJob: Job? = null

    init {
        getPosts(0, 10)
    }

    fun onEvent(event: PostsEvent) {
        when(event) {
            is PostsEvent.Read -> {
                if(event.start == state.value.start &&
                    event.limit == state.value.limit) {
                    return
                }
                getPosts(event.start, event.limit)
            }
            is PostsEvent.Detail -> TODO()
            is PostsEvent.Patch -> viewModelScope.launch {
                jsonUseCases.patchPostUseCase(event.postId, event.post)
            }
            is PostsEvent.Delete -> viewModelScope.launch {
                jsonUseCases.deletePostUseCase(event.postId)
            }
            PostsEvent.ReadFirst -> {
                _state.value = state.value.copy(
                    start = 0
                )
                getPosts(state.value.start, state.value.limit)
            }
            is PostsEvent.ReadMore -> {
                _state.value = state.value.copy(
                    start = state.value.start.plus(1)
                )
                getPosts(state.value.start, state.value.limit)
            }
        }
    }

    private fun getPosts(start: Int, limit: Int) {
        viewModelScope.launch {
            getPostsJob?.cancel()
            getPostsJob = jsonUseCases.getPostsUseCase(start, limit)
                .onEach { posts ->
                    _state.value = state.value.copy(
                        posts = state.value.posts + posts,
                        start = start,
                        limit = limit
                    )
                }
                .launchIn(viewModelScope)
        }
    }
}