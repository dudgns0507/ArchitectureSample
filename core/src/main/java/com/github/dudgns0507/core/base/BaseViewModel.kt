package com.github.dudgns0507.core.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.io.IOException

sealed class CommonError {
    object NetworkError : CommonError()
    class DataError(val message: String, val data: Any) : CommonError()
    class UnknownError(val throwable: Throwable? = null, error: IOException? = null) : CommonError()
}

open class BaseViewModel<T : BaseEvent> : ViewModel() {
    companion object {
        val TAG: String by lazy {
            val name = this::class.java.simpleName
            name.substring(name.lastIndexOf(".") + 1)
                .replace("ViewModel", "VM")
        }
    }

    private val _eventsFlow = MutableSharedFlow<T>()
    val eventsFlow: SharedFlow<T> = _eventsFlow

    open fun initialize() {}

    open fun onEvent(event: T) {
        when (event) {
            BaseEvent.Reload -> initialize()
        }
        viewModelScope.launch { _eventsFlow.emit(event) }
    }

    fun runEvent(event: T) {
        onEvent(event)
    }

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    private val _error = MutableLiveData<CommonError>()
    val error: LiveData<CommonError> = _error

    fun loading(state: Boolean) {
        _loading.value = state
    }

    protected fun showUnknownError(throwable: Throwable) {
        _error.postValue(CommonError.UnknownError(throwable))
    }

    protected fun showNetworkError(error: IOException? = null) {
        _error.postValue(CommonError.UnknownError(error))
    }

    protected fun showTokenError() {
        // Login Token Failed Auth
    }

    protected fun showDataError(message: String, data: Any) {
        _error.postValue(CommonError.DataError(message, data))
    }
}