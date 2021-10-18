package com.github.dudgns0507.mvvm.ui.fragment.first

import androidx.lifecycle.SavedStateHandle
import com.github.dudgns0507.core.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FirstViewModel @Inject constructor(
    state: SavedStateHandle
) : BaseViewModel(state) {
}