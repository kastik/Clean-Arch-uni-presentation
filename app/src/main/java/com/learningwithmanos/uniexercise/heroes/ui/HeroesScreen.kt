package com.learningwithmanos.uniexercise.heroes.ui

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import com.learningwithmanos.uniexercise.heroes.data.Hero

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HeroesScreen(

    //viewModel: HeroesViewModel = hiltViewModel()
) {
    HeroCard(Hero(5,"",5,""))
}
@Composable
fun ShowHeroes(heroes: List<HeroTileModel>) {

    //heroes.forEach {
    //    Text(text = it.title)
    //}
}