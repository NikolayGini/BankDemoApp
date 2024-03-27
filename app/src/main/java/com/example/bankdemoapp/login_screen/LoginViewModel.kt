package com.example.bankdemoapp.login_screen

import android.os.Parcelable
import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bankdemoapp.protocols.ViewModelProtocol
import com.example.bankdemoapp.protocols.SavedStateHandleWrapperProtocol
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.stateIn
import kotlinx.parcelize.Parcelize

private const val TAG = "LoginViewModel"

class LoginViewModel(
    override val savedSateHandle: SavedStateHandle
) : ViewModel(), ViewModelProtocol<LoginState, LoginState.Event> {
    override val savedSateWrapper = LoginScreenSavedStateHandleWrapper(savedSateHandle)

    override val state = savedSateWrapper.data.filterNotNull()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = LoginState()
        )


    override fun sendEvent(event: LoginState.Event) {
        when (event) {
            is LoginState.Event.EmailChanged -> updateEmail(email = event.value)
            is LoginState.Event.PasswordChanged -> updatePassword(password = event.value)
        }
    }

    private fun updateEmail(email: String) {
        savedSateWrapper.update {
            LoginState(
                email = email,
                password = it.password
            )
        }
    }

    private fun updatePassword(password: String) {
        savedSateWrapper.update {
            LoginState(
                email = it.email,
                password = password
            )
        }
    }

    class LoginScreenSavedStateHandleWrapper(
        override val handle: SavedStateHandle
    ) : SavedStateHandleWrapperProtocol<LoginState> {
        override val data: StateFlow<LoginState> =
            handle.getStateFlow(STATE_VALUE_KEY, LoginState())

        override fun update(function: (LoginState) -> LoginState) {
            val value: LoginState? = handle[STATE_VALUE_KEY]
            Log.e(TAG, "*** saved value -> $value")

            value?.let { handle[STATE_VALUE_KEY] = function(value) }
        }

        companion object {
            private const val STATE_VALUE_KEY = "state_value"
        }
    }
}

@Parcelize
data class LoginState(val email: String = "", val password: String = "") : Parcelable {
    sealed interface Event {
        data class EmailChanged(val value: String) : Event
        data class PasswordChanged(val value: String) : Event
    }
}


