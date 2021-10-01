package com.github.dudgns0507.core.util

import android.content.res.Resources
import android.os.Build
import java.util.*

object Utils {
    fun getScreenWidth(): Int {
        return Resources.getSystem().displayMetrics.widthPixels
    }

    fun getScreenHeight(): Int {
        return Resources.getSystem().displayMetrics.heightPixels
    }

    val deviceName: String
        get() {
            val manufacturer = Build.MANUFACTURER
            val model = Build.MODEL
            return if (model.startsWith(manufacturer))
                model.uppercase(Locale.getDefault())
            else
                manufacturer.uppercase(Locale.getDefault()) + " " + model
        }
}