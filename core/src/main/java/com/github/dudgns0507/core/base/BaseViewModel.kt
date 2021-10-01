package com.github.dudgns0507.core.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.github.dudgns0507.core.util.network.GenericError
import com.github.dudgns0507.core.util.network.ResultWrapper
import java.io.IOException

open class BaseViewModel(private val state: SavedStateHandle) : ViewModel() {
    private val TAG: String by lazy {
        javaClass.simpleName.substring(javaClass.simpleName.lastIndexOf(".") + 1)
            .replace("ViewModel", "VM")
    }

    private val _genericError = MutableLiveData<GenericError>()
    val genericError: LiveData<GenericError>
        get() = _genericError

    private val _netWorkError = MutableLiveData<String>()
    val netWorkError: LiveData<String>
        get() = _netWorkError

    private val _unKnownError = MutableLiveData<String>()
    val unKnownError: LiveData<String>
        get() = _unKnownError

    private fun showUnknownError(throwable: Throwable) {
        _unKnownError.postValue("UnknownError")
    }

    private fun showNetworkError(error: IOException) {
        _netWorkError.postValue("NetWorkError")
    }

    private fun showGenericError(error: GenericError) {
        _genericError.postValue(error)
        state.getLiveData<String>(TAG)
        state["1"] = ""
    }

    fun <T : Any, E : Any> ResultWrapper<T, E>.getData(): ResultWrapper.Success<T>? {
        when (this) {
            is ResultWrapper.NetworkError -> showNetworkError(this.error)
            is ResultWrapper.ApiError -> {
                this.error?.let {
                    when(it) {
                        is GenericError -> showGenericError(it)
                    }
                }
            }
            is ResultWrapper.UnknownError -> this.throwable?.let { showUnknownError(it) }
            is ResultWrapper.Success<T> -> return this
        }
        return null
    }
}