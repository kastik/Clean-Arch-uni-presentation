package com.learningwithmanos.uniexercise.ui.HeroScreen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.hilt.navigation.compose.hiltViewModel
import com.learningwithmanos.uniexercise.ui.HeroCard
import com.learningwithmanos.uniexercise.heroes.ui.HeroesViewModel
import com.learningwithmanos.uniexercise.ui.HeroScreen.HeroDetailsDialog.HeroDetailsDialog
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HeroesScreen(
    viewModel: HeroesViewModel = hiltViewModel()
) {
    val heroesList by rememberUpdatedState( viewModel.heroesStateFlow.collectAsState(initial = listOf()))
    val heroDetailsUIState by rememberUpdatedState(viewModel.heroDetailsUIState.collectAsState())

    AnimatedVisibility(visible = heroDetailsUIState.value.showPopup) {
        HeroDetailsDialog(onDismissRequest = { viewModel.dismissHeroDetails() }, hero = heroDetailsUIState.value.selectedHero!!)
    }

    if (heroesList.value.isEmpty()) {
        Column(modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {
            Text(
                text = "Download some heroes first \uD83D\uDE42",  //The weird chars are emoji :)
                style = MaterialTheme.typography.titleLarge
            )
        }
    }else {
        LazyColumn {
            items(heroesList.value) { hero ->
                HeroCard(
                    name = hero.name,
                    image = hero.imageUrl,
                    showQueryButton = false,
                    isInQueryScreen = false,
                    action1 = { viewModel.showHeroDetails(hero) },
                    query = {})
            }
        }
    }
}
