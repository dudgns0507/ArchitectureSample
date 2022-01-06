package com.github.dudgns0507.core.util.ext

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect

fun <T> LifecycleOwner.observe(liveData: LiveData<T>, observer: (t: T) -> Unit) {
    liveData.observe(this) { it?.let { t -> observer(t) } }
}

fun <T> LifecycleOwner.observe(state: StateFlow<T>, observer: (t: T) -> Unit) {
    state.asLiveData().observe(this) { it?.let { t -> observer(t) } }
}

fun <T> LifecycleOwner.observe(channel: Flow<T>, observer: (t: T) -> Unit) {
    channel.asLiveData().observe(this) { it?.let { t -> observer(t) } }
}

suspend fun <T> LifecycleOwner.collect(channel: Flow<T>, observer: (t: T) -> Unit) {
    channel.collect { t -> observer(t) }
}