package com.github.dudgns0507.mvvm.ui.post

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.github.dudgns0507.core.base.BaseViewModel
import com.github.dudgns0507.core.util.SingleEvent
import com.github.dudgns0507.domain.dto.Comment
import com.github.dudgns0507.domain.dto.Post
import com.github.dudgns0507.domain.repository.DataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(
    state: SavedStateHandle,
    private val dataRepository: DataRepository
) : BaseViewModel(state) {
    var bundle: Post? = null

    val deletePost = SingleEvent<String>()

    private val _comments = MutableLiveData<List<Comment>>()
    val comments: LiveData<List<Comment>> = _comments

    private val _post = MutableLiveData<Post>()
    val post: LiveData<Post> = _post

    fun requestPost(id: Int) = viewModelScope.launch {
        val response = dataRepository.requestPost(id)

//        response.getData()?.let { result ->
//            result.value.let {
//                _post.postValue(it)
//            }
//        }
    }

    fun requestComments(id: Int) = viewModelScope.launch {
        val response = dataRepository.requestPostComments(id)

//        response.getData()?.let { result ->
//            result.value.let {
//                _comments.postValue(it)
//            }
//        }
    }

    fun deletePost(id: Int) = viewModelScope.launch {
        val response = dataRepository.deletePost(id)
        deletePost.postValue("")
    }
}