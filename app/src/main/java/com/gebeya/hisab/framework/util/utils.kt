package com.gebeya.hisab.framework.util

import android.util.Log

private const val TAG = "HISAB-TAG-DEBUG"

fun logD(source: Any, message: String) {
    log(source, message)
}

fun logE(source: Any, message: String) {
    log(source, message, true)
}

fun log(source: Any, message: String, error: Boolean = false) {
    val name = source::class.simpleName
    val output = "$name : $message"
    if (error) {
        Log.e(TAG, output)
    } else {
        Log.d(TAG, output)
    }
}