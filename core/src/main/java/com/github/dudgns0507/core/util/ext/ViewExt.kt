package com.github.dudgns0507.core.util.ext

import android.view.View
import com.google.android.material.snackbar.Snackbar

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun View.snackBar(message: String, action: String = "", listener: View.OnClickListener? = null) {
    if(action.isNotBlank() && listener != null) {
        Snackbar.make(this, message, Snackbar.LENGTH_LONG).setAction(action, listener).show()
    } else {
        Snackbar.make(this, message, Snackbar.LENGTH_LONG).show()
    }
}