package com.example.bankdemoapp.logout_screen

import android.os.Bundle
import android.view.View
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.bankdemoapp.R

class LogOutFragment : Fragment(R.layout.content) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<ComposeView>(R.id.compose_view).setContent {
            LogoutScreen(onLogoutClick = { findNavController().popBackStack()} )
        }
    }
}