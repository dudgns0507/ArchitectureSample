package com.github.dudgns0507.core.util.ext

import java.text.DecimalFormat

fun String.toPrice(): String {
    val dec = DecimalFormat("###,###,###")
    return dec.format(this.toDouble())
}

fun Double.toPrice(): String {
    val dec = DecimalFormat("###,###,###")
    return dec.format(this)
}

fun Float.toPrice(): String {
    val dec = DecimalFormat("###,###,###")
    return dec.format(this)
}

fun Int.toPrice(): String {
    val dec = DecimalFormat("###,###,###")
    return dec.format(this)
}

fun Long.toPrice(): String {
    val dec = DecimalFormat("###,###,###")
    return dec.format(this)
}