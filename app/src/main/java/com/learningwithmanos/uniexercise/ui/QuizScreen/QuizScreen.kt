package com.learningwithmanos.uniexercise.ui.QuizScreen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.learningwithmanos.uniexercise.heroes.data.Hero
import com.learningwithmanos.uniexercise.heroes.usecase.QuizHeroesUC
import javax.inject.Inject

@Composable
@Preview
fun QuizScreen(
    viewModel: QuizViewModel = hiltViewModel()
) {
    val hero = viewModel.getRandomHero()

    Row {
        Text(text = "Quiz")
    }
    Column {
        AsyncImage(model = hero?.imageUrl, contentDescription = "")
        Text(text = "Who is this hero")
    }
    HorizontalDivider()

    val radioOptions = listOf("SpiderMan", "AquaMan", hero?.name, "BatMan")
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[0]) }
    val showFirstTab = remember { mutableStateOf(true) }
    var showAlert = false
// Note that Modifier.selectableGroup() is essential to ensure correct accessibility behavior
    AnimatedVisibility(visible = showFirstTab.value) {
        Column(Modifier.selectableGroup()) {
            radioOptions.forEach { text ->
                Row(
                    Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .selectable(
                            selected = (text == selectedOption),
                            onClick = { onOptionSelected(text) },
                            role = Role.RadioButton
                        )
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = (text == selectedOption),
                        onClick = null // null recommended for accessibility with screenreaders
                    )
                    if (text != null) {
                        Text(
                            text = text,
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.padding(start = 16.dp)
                        )
                    }
                }

            }
            Button(onClick = {
                if (hero?.name == selectedOption) {
                    showAlert = true
                } }) {
                Text(text = "Next")
            }

            if (showAlert) {
                AlertDialog(
                    onDismissRequest = { showAlert = false },
                    title = { Text("Hero Matched") },
                    text = { Text("You selected the correct hero: $selectedOption") },
                    confirmButton = {
                        Button(onClick = { showAlert = false }) {
                            Text("OK")
                        }
                    }
                )
            } else {
                AlertDialog(
                    onDismissRequest = { showAlert = false },
                    title = { Text("Hero Not Matched") },
                    text = { Text("You selected the wrong hero: $selectedOption") },
                    confirmButton = {
                        Button(onClick = { showAlert = false }) {
                            Text("OK")
                        }
                    }
                )
            }
        }

    }
}