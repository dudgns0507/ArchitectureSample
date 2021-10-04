package com.github.dudgns0507.mvvm.ui.main

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.github.dudgns0507.core.base.BaseViewModel
import com.github.dudgns0507.domain.usecase.JsonUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    state: SavedStateHandle,
    private val jsonUseCases: JsonUseCases
) : BaseViewModel(state) {
    private val _state = MutableStateFlow(PostsState())
    val state: StateFlow<PostsState> = _state

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
        getPostsJob?.cancel()
        getPostsJob = viewModelScope.launch {
            jsonUseCases.getPostsUseCase(start, limit)
                .onStart {
                    // Loading
                }
                .catch { exception ->
                    // Hide Loading
                    // Error Handling
                }
                .collect { baseResult ->
                    // Hide Loading
                    // Result Handling
//                    when(baseResult){
//                        is BaseResult.Error -> state.value = LoginActivityState.ErrorLogin(baseResult.rawResponse)
//                        is BaseResult.Success -> state.value = LoginActivityState.SuccessLogin(baseResult.data)
//                    }
                }
        }
    }
}