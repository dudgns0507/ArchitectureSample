package com.github.dudgns0507.core.util.ext

import androidx.compose.runtime.State
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

fun <T> LifecycleOwner.observe(liveData: LiveData<T>, observer: (t: T) -> Unit) {
    liveData.observe(this, { it?.let { t -> observer(t) } })
}