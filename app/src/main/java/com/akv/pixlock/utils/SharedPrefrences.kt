package com.akv.pixlock.utils

import android.content.Context

fun saveValue(context: Context, key: String, value: String) {
    val sharedPreferences = context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
    val editor = sharedPreferences.edit()
    editor.putString(key, value)
    editor.apply()
}

fun getValue(context: Context, key: String): String? {

    val sharedPreferences = context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
    return sharedPreferences.getString(key, null)
}