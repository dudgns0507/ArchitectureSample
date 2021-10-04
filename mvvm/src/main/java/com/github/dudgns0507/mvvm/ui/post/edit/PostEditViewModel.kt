package com.github.dudgns0507.mvvm.ui.post.edit

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.github.dudgns0507.core.base.BaseViewModel
import com.github.dudgns0507.core.util.SingleEvent
import com.github.dudgns0507.domain.dto.Post
import com.github.dudgns0507.domain.repository.DataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostEditViewModel @Inject constructor(
    state: SavedStateHandle,
    private val dataRepository: DataRepository
) : BaseViewModel(state) {
    val editPost = SingleEvent<String>()

    fun patchPost(id: Int, post: Post) = viewModelScope.launch {
        val response = dataRepository.patchPost(id, post)

//        response.getData()?.let { result ->
//            result.value.let {
//                editPost.postValue("")
//            }
//        }
    }
}