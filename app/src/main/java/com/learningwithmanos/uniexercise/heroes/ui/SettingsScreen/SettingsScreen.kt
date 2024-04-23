package com.learningwithmanos.uniexercise.heroes.ui.SettingsScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview


@Preview
@Composable
fun SettingsScreen() {
    val text = remember{ mutableStateOf("") }
    Column(Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Enter Your API Keys", style = MaterialTheme.typography.headlineMedium)//TODO PADDING


        OutlinedTextField(value =text.value , onValueChange = {})
        OutlinedTextField(value =text.value , onValueChange = {})

    }

}