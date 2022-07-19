package com.dynamicviewcreator

import android.content.Context
import android.util.TypedValue
import kotlin.math.roundToInt

fun Context.dpToPx(dp: Int): Int {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        dp.toFloat(),
        this.resources.displayMetrics
    ).roundToInt()
}

fun Context.spToPx(sp: Float): Float {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_SP,
        sp, this.resources.displayMetrics)
}

fun String.toPropertyTitle(): String {
    val text = this.replace("\\d+".toRegex(), "")
    val list = text.split("(?=[A-Z])".toRegex()).toMutableList()
    return if (list.isNotEmpty()) {
        list[0] = list[0].replaceFirstChar { it.uppercase() }
        list.joinToString(" ", "", ":")
    } else this
}