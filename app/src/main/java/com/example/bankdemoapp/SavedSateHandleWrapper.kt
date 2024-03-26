package com.example.bankdemoapp

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import kotlinx.coroutines.flow.StateFlow

class SavedStateHandleWrapper<T>(private val handle: SavedStateHandle) {

    companion object {

        private const val VALUE_KEY = "value_key"
    }

    val data: StateFlow<T?> = handle.getStateFlow<T?>(VALUE_KEY, null)

    fun update(function: (T?) -> T) {

        val value: T? = handle[VALUE_KEY]
        Log.e("app_log", "*** saved value -> $value")

        value.let { handle[VALUE_KEY] = function(value) }
    }
}