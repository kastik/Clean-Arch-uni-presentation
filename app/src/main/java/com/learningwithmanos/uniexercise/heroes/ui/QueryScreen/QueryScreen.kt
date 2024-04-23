package com.learningwithmanos.uniexercise.heroes.ui.QueryScreen

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.learningwithmanos.uniexercise.heroes.data.Hero
import com.learningwithmanos.uniexercise.heroes.ui.HeroCard


@Composable
fun QueryScreen(
    viewModel: QueryViewModel = hiltViewModel()
){
    val test = viewModel.heroesStateFlow.collectAsState(initial = emptyList())
    LazyColumn {
        items(test.value){
            HeroCard(data = it, show = false )
        }

    }

}


@Preview
@Composable
fun prev(){
    //QueryScreen(Hero(1,"",1,"",""))
}