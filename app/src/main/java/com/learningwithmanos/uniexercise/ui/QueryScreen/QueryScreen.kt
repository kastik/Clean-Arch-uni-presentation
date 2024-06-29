package com.learningwithmanos.uniexercise.ui.QueryScreen

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.learningwithmanos.uniexercise.ui.HeroCard
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.LaunchedEffect


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QueryScreen(
    searchText: MutableState<String>,
    viewModel: QueryViewModel = hiltViewModel()
){
    val heroes = viewModel.heroesStateFlow.collectAsState(initial = emptyList()) //GET INIT HEROES

    LaunchedEffect(searchText.value) {
        viewModel.executeQuery(searchText.value)
    }

    LazyColumn{
        items(heroes.value){ hero ->
            HeroCard(
                name = hero.name,
                image = hero.imageUrl,
                showQueryButton = false,
                isInQueryScreen = true,
                action1 = {viewModel.storeHero(hero)},
                query = {})
        }
    }

}
