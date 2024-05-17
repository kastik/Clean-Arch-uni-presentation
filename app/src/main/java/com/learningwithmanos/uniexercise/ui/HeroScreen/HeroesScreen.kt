package com.learningwithmanos.uniexercise.ui.HeroScreen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.learningwithmanos.uniexercise.ui.HeroCard
import com.learningwithmanos.uniexercise.heroes.ui.HeroesViewModel
import com.learningwithmanos.uniexercise.ui.HeroScreen.HeroDetailsDialog.HeroDetailsDialog

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HeroesScreen(
    viewModel: HeroesViewModel = hiltViewModel()
) {
    val heroesList = viewModel.heroesStateFlow.collectAsState(initial = listOf())
    val heroDetailsUIState = viewModel.heroDetailsUIState.collectAsState()

    AnimatedVisibility(visible = heroDetailsUIState.value.showPopup) {
        HeroDetailsDialog(onDismissRequest = { viewModel.dismissHeroDetails() }, hero = heroDetailsUIState.value.selectedHero!!)
    }

    LazyColumn {
        items(heroesList.value.size) {
            HeroCard(
                heroesList.value[it].name,
                heroesList.value[it].imageUrl,
                false,false,{viewModel.showHeroDetails(heroesList.value[it])},{})
        }
    }

}
