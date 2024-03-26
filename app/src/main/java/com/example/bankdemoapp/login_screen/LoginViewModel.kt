package com.example.bankdemoapp.login_screen

import android.os.Parcelable
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.bankdemoapp.BaseViewModel
import com.example.bankdemoapp.login_screen.LoginState.ValuesUpdated
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.stateIn
import kotlinx.parcelize.Parcelize

class LoginViewModel(
    savedSateHandle: SavedStateHandle
) : BaseViewModel<LoginState, LoginEvent>(savedSateHandle = savedSateHandle) {

    override val state = savedSateWrapper.data.filterNotNull()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = ValuesUpdated()
        )


    override fun sendEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.UpdateEmail -> updateEmail(email = event.value)
            is LoginEvent.UpdatePassword -> updatePassword(password = event.value)
        }
    }

    private fun updateEmail(email: String) {
        savedSateWrapper.update {
            ValuesUpdated(
                email = email,
                password = if (it is ValuesUpdated) it.password else ""
            )
        }
    }

    private fun updatePassword(password: String) {
        savedSateWrapper.update {
            ValuesUpdated(
                email = if (it is ValuesUpdated) it.email else "",
                password = password
            )
        }
    }
}

sealed interface LoginState : Parcelable {

    @Parcelize
    data class ValuesUpdated(val email : String = "", val password : String = "") : LoginState
}

sealed interface LoginEvent {

    data class UpdateEmail(val value: String) : LoginEvent

    data class UpdatePassword(val value: String) : LoginEvent
}


