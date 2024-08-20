package com.plcoding.composepaging3caching.login.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
) {
    Scaffold(
        topBar = {

        },
        bottomBar = {

        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {
                TextField(value = viewModel.state.email, onValueChange = { viewModel.updateEmail(it) })
                TextField(value = viewModel.state.password, onValueChange = { viewModel.updatePassword(it) })
                Button(onClick = { viewModel.login() }) {
                    Text(text = "Login")
                }
                Text(text = viewModel.state.token)
            }
        }
    )
}