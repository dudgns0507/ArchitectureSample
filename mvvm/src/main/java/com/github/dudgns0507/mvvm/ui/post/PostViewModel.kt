package com.github.dudgns0507.mvvm.ui.post

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.github.dudgns0507.core.base.BaseViewModel
import com.github.dudgns0507.core.util.SingleEvent
import com.github.dudgns0507.core.util.network.Resource
import com.github.dudgns0507.domain.dto.Comment
import com.github.dudgns0507.domain.dto.InvalidPostException
import com.github.dudgns0507.domain.dto.Post
import com.github.dudgns0507.domain.repository.DataRepository
import com.github.dudgns0507.domain.usecase.JsonUseCases
import com.github.dudgns0507.mvvm.ui.main.PostsEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(
    state: SavedStateHandle,
    private val jsonUseCases: JsonUseCases
) : BaseViewModel(state) {
    var bundle: Post? = null

    val error = SingleEvent<String>()
    val deletePost = SingleEvent<String>()

    private val _comments = MutableLiveData(emptyList<Comment>())
    val comments: LiveData<List<Comment>> = _comments

    private val _post = MutableLiveData(Post())
    val post: LiveData<Post> = _post

    fun onEvent(event: PostDetailEvents) {
        when(event) {
            is PostDetailEvents.Delete -> viewModelScope.launch {
                jsonUseCases.deletePostUseCase(event.postId)
                deletePost.postValue("")
            }
            is PostDetailEvents.Patch -> viewModelScope.launch {
                jsonUseCases.patchPostUseCase(event.postId, event.post)
                    .catch { e ->
                        when(e) {
                            is InvalidPostException -> error.postValue(e.message)
                        }
                    }
            }
            is PostDetailEvents.Read -> {
                requestPost(event.postId)
                requestComments(event.postId)
            }
        }
    }

    fun requestPost(id: Int) = viewModelScope.launch {
        jsonUseCases.getPostUseCase(id)
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
                    is Resource.Success ->  _post.value = result.data
                    is Resource.Failure -> TODO()
                    is Resource.Loading -> TODO()
                }
            }
    }

    fun requestComments(id: Int) = viewModelScope.launch {
        jsonUseCases.getCommentsUseCase(id)
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
                    is Resource.Success ->  _comments.value = result.data
                    is Resource.Failure -> TODO()
                    is Resource.Loading -> TODO()
                }
            }
    }
}