package com.learningwithmanos.uniexercise.heroes.ui.QueryScreen

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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QueryScreen(
    viewModel: QueryViewModel = hiltViewModel()
){
    val heroes = viewModel.heroesStateFlow.collectAsState(initial = emptyList())
    LazyColumn(
        modifier = Modifier.nestedScroll(
            TopAppBarDefaults.pinnedScrollBehavior(
                rememberTopAppBarState()
            ).nestedScrollConnection)
    ) {
        items(heroes.value.size){
            HeroCard(heroes.value[it].title, heroes.value[it].imageUrl, show = false )
        }
    }

}