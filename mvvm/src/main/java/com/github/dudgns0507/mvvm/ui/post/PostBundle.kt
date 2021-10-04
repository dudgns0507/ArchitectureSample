package com.github.dudgns0507.mvvm.ui.post

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PostBundle(
    val t: String
) : Parcelable