package com.example.bankdemoapp.loguout_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun LogoutScreen(
    modifier: Modifier = Modifier,
    onLogoutClick: () -> Unit
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        val savedText = rememberSaveable { mutableStateOf("") }

        TextField(
            value = savedText.value,
            onValueChange = { savedText.value = it },
            label = { Text(text = "Test process death recovery") },
            placeholder = { Text(text = "Enter any text") }
        )

        Button(
            modifier = Modifier.padding(32.dp),
            onClick = onLogoutClick
        ) {
            Text("Log out")
        }
    }
}