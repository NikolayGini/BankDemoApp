package com.example.bankdemoapp.login_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun LoginScreen(
    loginState: State<LoginState>,
    updateEmail: (email: String) -> Unit,
    updatePassword: (email: String) -> Unit,
    onToLogoutScreenButtonClick: () -> Unit,
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        when (val state = loginState.value) {
            is LoginState.ValuesUpdated -> {
                ValuesState(
                    emailState = state.email,
                    passwordState = state.password,
                    updateEmail = updateEmail,
                    updatePassword = updatePassword,
                    onToLogoutScreenButtonClick = onToLogoutScreenButtonClick
                )
            }
        }
    }
}

@Composable
private fun ValuesState(
    emailState: String,
    passwordState: String,
    updateEmail: (email: String) -> Unit,
    updatePassword: (email: String) -> Unit,
    onToLogoutScreenButtonClick: () -> Unit,
) {
    TextField(value = emailState, onValueChange = updateEmail)

    var passwordVisible by rememberSaveable { mutableStateOf(false) }
    val visualTransformation = if (passwordVisible) {
        VisualTransformation.None
    } else {
        PasswordVisualTransformation()
    }

    TextField(
        modifier = Modifier.padding(top = 24.dp),
        value = passwordState,
        onValueChange = updatePassword,
        label = { Text("Password") },
        singleLine = true,
        placeholder = { Text("Password") },
        visualTransformation = visualTransformation,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
    )

    Button(
        modifier = Modifier.padding(top = 60.dp),
        onClick = onToLogoutScreenButtonClick,
    ) {
        Text("login")
    }
}

@Preview
@Composable
fun PreviewLoginScreen() {
    val email = remember { mutableStateOf(value = "nikolayk@gini") }
    val password = remember { mutableStateOf(value = "13414") }

}