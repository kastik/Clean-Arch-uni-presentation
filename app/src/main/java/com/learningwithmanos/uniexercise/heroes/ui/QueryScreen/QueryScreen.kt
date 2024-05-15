package com.learningwithmanos.uniexercise.heroes.ui.QueryScreen

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.learningwithmanos.uniexercise.heroes.ui.HeroCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QueryScreen(
    searchText: MutableState<String>,
    viewModel: QueryViewModel = hiltViewModel()
){
    val heroes = viewModel.heroesStateFlow.collectAsState(initial = emptyList()) //GET INIT HEROES



    LazyColumn{
        items(heroes.value.size){
            HeroCard(heroes.value[it].name, heroes.value[it].imageUrl, show = false) {
                viewModel.storeHero(heroes.value[it])
            }
        }
    }

}
