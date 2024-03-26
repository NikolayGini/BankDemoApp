package com.example.bankdemoapp.splash_screen

import android.os.Bundle
import android.view.View
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.bankdemoapp.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashFragment : Fragment(R.layout.content) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<ComposeView>(R.id.compose_view).setContent {
            Box(
                modifier = Modifier.background(Color(0xFFB9E686)),
                contentAlignment = Alignment.Center
            ) {
                Text("SPLASH SCREEN")
            }

            launchNavigationTimer()
        }
    }

    private fun launchNavigationTimer(delay: Long = 2000) {
        lifecycleScope.launch {
            delay(delay)
            findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
        }
    }
}