package com.learningwithmanos.uniexercise.heroes.ui.HeroScreen

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.hilt.navigation.compose.hiltViewModel
import com.learningwithmanos.uniexercise.heroes.ui.HeroCard
import com.learningwithmanos.uniexercise.heroes.ui.HeroesViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HeroesScreen(
    viewModel: HeroesViewModel = hiltViewModel()
) {
    // val selectedTab = viewModel.selectedTabStateFlow.collectAsState()
    val heroesList = viewModel.heroesStateFlow.collectAsState(initial = listOf())
    LazyColumn(
        modifier = Modifier.nestedScroll(
            TopAppBarDefaults.pinnedScrollBehavior(
                rememberTopAppBarState()
            ).nestedScrollConnection)
    ) {
        items(heroesList.value.size) {
            HeroCard(heroesList.value[it].title, heroesList.value[it].imageUrl, false) //TODO Display List
        }
    }

}

/*@Composable
fun ShowHeroes(heroes: List<HeroTileModel>) {
    heroes.forEach {
        Text(text = it.title)
    }
}*/

/*@Preview
@Composable
fun paok(){
    HeroCard(data = Hero(1,"Doctor Strange",1,"",""), show = false)
}*/
