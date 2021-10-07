package com.github.dudgns0507.core.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import java.io.IOException

open class BaseViewModel(private val state: SavedStateHandle) : ViewModel() {
    companion object {
        val TAG: String by lazy {
            val name = this::class.java.simpleName
            name.substring(name.lastIndexOf(".") + 1)
                .replace("ViewModel", "VM")
        }
    }

    private val _genericError = MutableLiveData<String>()
    val genericError: LiveData<String>
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

    private fun showGenericError(error: String) {
        _genericError.postValue(error)
    }
}