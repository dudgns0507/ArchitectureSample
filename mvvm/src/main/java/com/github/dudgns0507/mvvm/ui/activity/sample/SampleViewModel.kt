package com.github.dudgns0507.mvvm.ui.activity.sample

import androidx.lifecycle.SavedStateHandle
import com.github.dudgns0507.core.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SampleViewModel @Inject constructor(
    state: SavedStateHandle
) : BaseViewModel(state) {
}