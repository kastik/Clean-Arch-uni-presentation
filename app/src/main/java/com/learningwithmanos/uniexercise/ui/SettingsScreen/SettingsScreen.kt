package com.learningwithmanos.uniexercise.ui.SettingsScreen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.learningwithmanos.uniexercise.ui.AvailableScreens
import dagger.hilt.android.lifecycle.HiltViewModel


@Composable
@ExperimentalMaterial3Api
fun SettingsScreen(
    goBack: () -> Unit,
    vIewModel: SettingsScreenVIewModel = hiltViewModel(),
) {
    val publicKey: String = vIewModel.apiKeyStateFlow.collectAsState().value
    val privateKey: String = vIewModel.privateKeyStateFlow.collectAsState().value
    val isButtonEnabled: Boolean = vIewModel.isButtonEnabledStateFlow.collectAsState().value
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Settings") },
                navigationIcon = {
                    IconButton(onClick = goBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, "")
                    }
                }) //TODO SHOULD NOT BE STATIC BUT wt



        }
    ) {pad  ->
        Column(
            Modifier.fillMaxSize().padding(pad),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "Enter Your API Keys",
                style = MaterialTheme.typography.headlineMedium
            )//TODO PADDING


            Text(
                text = "Public Key",
                style = MaterialTheme.typography.bodyLarge
            )


            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = publicKey,
                onValueChange = {
                    vIewModel.updateApiKey(it)
                },

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
                },

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

}