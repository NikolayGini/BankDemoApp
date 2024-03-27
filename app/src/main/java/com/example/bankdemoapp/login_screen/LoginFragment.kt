package com.example.bankdemoapp.login_screen

import android.os.Bundle
import android.view.View
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.bankdemoapp.R

class LoginFragment : Fragment(R.layout.content) {

    private val viewModel by viewModels<LoginViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<ComposeView>(R.id.compose_view).setContent {
            LoginScreen(
                loginState = viewModel.state.collectAsState().value,
                updateEmail = { viewModel.sendEvent(event = LoginState.Event.EmailChanged(value = it)) },
                updatePassword = { viewModel.sendEvent(event = LoginState.Event.PasswordChanged(value = it)) },
                onToLogoutScreenButtonClick = ::navigateToLogoutScreen
            )
        }
    }

    private fun navigateToLogoutScreen() {
        findNavController().navigate(R.id.action_loginFragment_to_logOutFragment)
    }
}