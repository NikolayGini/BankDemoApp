package com.example.bankdemoapp

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.bankdemoapp.login_screen.LoginState
import kotlinx.coroutines.flow.StateFlow

abstract class BaseViewModel<State, Event>(
    savedSateHandle: SavedStateHandle
) : ViewModel() {

    protected val savedSateWrapper = SavedStateHandleWrapper<LoginState>(handle = savedSateHandle)

    abstract val state: StateFlow<State>

    abstract fun sendEvent(event: Event)
}