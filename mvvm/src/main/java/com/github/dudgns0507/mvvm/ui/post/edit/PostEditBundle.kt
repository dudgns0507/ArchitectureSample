package com.github.dudgns0507.mvvm.ui.post.edit

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PostEditBundle(
    val userId: Int = 0,
    val id: Int = 0,
    val title: String = "",
    val body: String = ""
) : Parcelable