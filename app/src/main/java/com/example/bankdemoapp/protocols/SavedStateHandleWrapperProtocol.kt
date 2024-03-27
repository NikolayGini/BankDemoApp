package com.example.bankdemoapp.protocols

import androidx.lifecycle.SavedStateHandle
import kotlinx.coroutines.flow.StateFlow

interface SavedStateHandleWrapperProtocol<T> {
    val handle: SavedStateHandle
    val data: StateFlow<T>
    fun update(function: (T) -> T)
}