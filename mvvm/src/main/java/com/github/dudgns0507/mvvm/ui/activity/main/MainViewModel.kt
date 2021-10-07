package com.github.dudgns0507.mvvm.ui.activity.main

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.github.dudgns0507.core.base.BaseViewModel
import com.github.dudgns0507.core.util.ext.request
import com.github.dudgns0507.core.util.network.Resource
import com.github.dudgns0507.domain.usecase.JsonUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    state: SavedStateHandle,
    private val jsonUseCases: JsonUseCases
) : BaseViewModel(state) {

    private val _postStates = MutableStateFlow(MainPostsState())
    val postStates: StateFlow<MainPostsState> = _postStates

    init {
        getPosts(0, 10)
    }

    fun onEvent(event: MainPostsEvent) {
        when(event) {
            is MainPostsEvent.Read -> {
                if(event.start == postStates.value.start &&
                    event.limit == postStates.value.limit
                ) {
                    return
                }
                getPosts(event.start, event.limit)
            }
            is MainPostsEvent.Patch -> viewModelScope.launch {
                jsonUseCases.patchPostUseCase(event.postId, event.post)
            }
            is MainPostsEvent.Delete -> viewModelScope.launch {
                jsonUseCases.deletePostUseCase(event.postId)
            }
            MainPostsEvent.ReadFirst -> {
                _postStates.value = postStates.value.copy(
                    posts = emptyList(),
                    start = 0
                )
                getPosts(postStates.value.start, postStates.value.limit)
            }
            is MainPostsEvent.ReadMore -> {
                _postStates.value = postStates.value.copy(
                    start = postStates.value.start.plus(1)
                )
                getPosts(postStates.value.start, postStates.value.limit)
            }
        }
    }

    private fun getPosts(start: Int, limit: Int) {
        viewModelScope.launch {
            jsonUseCases.getPostsUseCase(start, limit)
                .onStart {
                    // Loading
                }
                .catch {
                    // Hide Loading
                    // Error Handling
                }
                .collect { result ->
                    // Hide Loading
                    // Result Handling
                    when(result) {
                        is Resource.Success -> {
                            postStates.value.let {
                                _postStates.value = it.copy(
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

    private fun getPostsEx1(start: Int, limit: Int) {
        viewModelScope.launch {
            when(val result = jsonUseCases.getPostsUseCaseEx1(start, limit)) {
                is Resource.Success -> {
                    postStates.value.let {
                        _postStates.value = it.copy(
                            posts = it.posts + result.data
                        )
                    }
                }
                is Resource.Failure -> TODO()
            }
        }
    }

    private fun getPostsEx2(start: Int, limit: Int) {
        viewModelScope.launch {
            val result = jsonUseCases.getPostsUseCaseEx2(start, limit)
            postStates.value.let {
                _postStates.value = it.copy(
                    posts = it.posts + result
                )
            }
        }
    }

    /**
     * request(onResponse, onFailure) - extension method in core module
     */
    private fun getPostsEx3(start: Int, limit: Int) {
        val result = jsonUseCases.getPostsUseCaseEx3(start, limit).request(
            onResponse = { _, response ->
                response.body()?.let { body ->
                    postStates.value.let {
                        _postStates.value = it.copy(
                            posts = it.posts + body
                        )
                    }
                }
            },
            onFailure = { _, _ ->

            }
        )
    }
}