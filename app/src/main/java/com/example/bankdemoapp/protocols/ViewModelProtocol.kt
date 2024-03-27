package com.example.bankdemoapp.protocols

import androidx.lifecycle.SavedStateHandle
import kotlinx.coroutines.flow.StateFlow

interface ViewModelProtocol<State, in Event> {
    val savedSateHandle: SavedStateHandle
    val savedSateWrapper: SavedStateHandleWrapperProtocol<State>
    val state: StateFlow<State>
    fun sendEvent(event: Event)
}