package com.learningwithmanos.uniexercise.heroes.ui.HeroScreen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.learningwithmanos.uniexercise.heroes.data.Hero
import com.learningwithmanos.uniexercise.heroes.ui.HeroCard

@Composable
fun HeroesScreen(
    viewModel: HeroesViewModel = hiltViewModel()
) {
    // val selectedTab = viewModel.selectedTabStateFlow.collectAsState()
    val heroesList = viewModel.heroesStateFlow.collectAsState(initial = listOf())

    HeroCard(data = Hero(1,"",1,""),false) //TODO Display List


}

@Composable
fun ShowHeroes(heroes: List<HeroTileModel>) {
    heroes.forEach {
        Text(text = it.title)
    }
}

@Preview
@Composable
fun paok(){
    HeroCard(data = Hero(1,"Doctor Strange",1,""), show = false)
}
