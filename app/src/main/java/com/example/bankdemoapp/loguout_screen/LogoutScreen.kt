package com.example.bankdemoapp.loguout_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment

@Composable
fun LogoutScreen(onLogoutClick: () -> Unit) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        var savedText = rememberSaveable { mutableStateOf("") }

        TextField(value = savedText.value, onValueChange = { savedText.value = it })
        Button(onClick = onLogoutClick) {
            Text("Log out")
        }
    }
}