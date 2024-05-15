package com.learningwithmanos.uniexercise.ui.SettingsScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import dagger.hilt.android.lifecycle.HiltViewModel


@Composable
@ExperimentalMaterial3Api
fun SettingsScreen(
    vIewModel: SettingsScreenVIewModel = hiltViewModel(),
) {
    val publicKey: String = vIewModel.apiKeyStateFlow.collectAsState().value
    val privateKey: String = vIewModel.privateKeyStateFlow.collectAsState().value
    val isButtonEnabled: Boolean = vIewModel.isButtonEnabledStateFlow.collectAsState().value

    Column(Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Enter Your API Keys", style = MaterialTheme.typography.headlineMedium)//TODO PADDING


        Text(
            text = "Public Key",
            style = MaterialTheme.typography.bodyLarge
        )


        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = publicKey,
            onValueChange = {
                vIewModel.updateApiKey(it)
            } ,

            placeholder = { Text(text = "e.g. Hexamine") },
        )
        Text(
            text = "Private Key",
            style = MaterialTheme.typography.bodyLarge
        )


        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = privateKey,
            onValueChange = {
                vIewModel.updatePrivateKey(it)
            } ,

            placeholder = { Text(text = "e.g. Hexamine") },
        )
        Button(
            onClick = {
                vIewModel.updateApi(publicKey, privateKey)
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = isButtonEnabled
        ) {
            Text(text = "Save")
        }

    }

}