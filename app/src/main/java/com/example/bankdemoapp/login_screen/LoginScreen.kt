package com.example.bankdemoapp.login_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    loginState: LoginState,
    updateEmail: (String) -> Unit,
    updatePassword: (String) -> Unit,
    onToLogoutScreenButtonClick: () -> Unit,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        LoginForm(
            emailState = loginState.email,
            passwordState = loginState.password,
            updateEmail = updateEmail,
            updatePassword = updatePassword,
            onToLogoutScreenButtonClick = onToLogoutScreenButtonClick
        )
    }
}

@Composable
private fun LoginForm(
    modifier: Modifier = Modifier,
    emailState: String,
    passwordState: String,
    updateEmail: (String) -> Unit,
    updatePassword: (String) -> Unit,
    onToLogoutScreenButtonClick: () -> Unit,
) {
    Column(modifier = modifier.wrapContentSize()) {
        EmailField(
            email = emailState,
            onEmailChanged = updateEmail
        )

        PasswordField(
            modifier = Modifier.padding(top = 24.dp),
            password = passwordState,
            onPasswordChanged = updatePassword
        )

        FormButton(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 32.dp)
        ) {
            onToLogoutScreenButtonClick()
        }
    }
}

@Composable
fun EmailField(
    modifier: Modifier = Modifier,
    email: String,
    onEmailChanged: (String) -> Unit
) {
    TextField(
        modifier = modifier,
        value = email,
        onValueChange = onEmailChanged,
        label = { Text(text = "Email") },
        singleLine = true,
        placeholder = { Text(text = "Enter email address") }
    )
}

@Composable
fun PasswordField(
    modifier: Modifier = Modifier,
    password: String,
    onPasswordChanged: (String) -> Unit
) {
    val passwordVisible by rememberSaveable { mutableStateOf(false) }
    val visualTransformation = if (passwordVisible) {
        VisualTransformation.None
    } else {
        PasswordVisualTransformation()
    }

    TextField(
        modifier = modifier,
        value = password,
        onValueChange = onPasswordChanged,
        label = { Text("Password") },
        singleLine = true,
        placeholder = { Text("Enter password") },
        visualTransformation = visualTransformation,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
    )
}

@Composable
fun FormButton(
    modifier: Modifier = Modifier,
    onButtonClicked: () -> Unit
) {
    Button(
        modifier = modifier,
        onClick = onButtonClicked,
    ) {
        Text("login")
    }
}

@Preview(showBackground = true)
@Composable
fun EmailFieldPreview() {
    EmailField(email = "dang@gini-apps.com") { }
}

@Preview(showBackground = true)
@Composable
fun PasswordFieldPreview() {
    PasswordField(password = "password") { }
}

@Preview(showBackground = true)
@Composable
fun FormButtonPreview() {
    FormButton { }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun PreviewLoginScreen() {
    val email = "dang@gini-apps.com"
    val password = "13414"
    val loginState by remember {
        mutableStateOf(LoginState(email, password))
    }

    Box(modifier = Modifier.fillMaxSize()) {
        LoginScreen(
            modifier = Modifier.align(Alignment.Center),
            loginState = loginState,
            updateEmail = { },
            updatePassword = { },
            onToLogoutScreenButtonClick = { }
        )
    }
}