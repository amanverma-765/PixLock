package com.akv.pixlock.utils

import android.content.Context

fun saveValue(ctx: Context, key: String, value: String) {
    val sharedPreferences = ctx.getSharedPreferences(ctx.packageName, Context.MODE_PRIVATE)
    val editor = sharedPreferences.edit()
    editor.putString(key, value)
    editor.apply()
}

fun getValue(ctx: Context, key: String): String? {

    val sharedPreferences = ctx.getSharedPreferences(ctx.packageName, Context.MODE_PRIVATE)
    return sharedPreferences.getString(key, null)
}