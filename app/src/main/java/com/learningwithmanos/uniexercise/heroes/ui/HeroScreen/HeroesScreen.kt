package com.learningwithmanos.uniexercise.heroes.ui.HeroScreen

import android.util.Log
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
    val heroesList = viewModel.heroesStateFlow.collectAsState(initial = listOf())
    LazyColumn(
        modifier = Modifier.nestedScroll(
            TopAppBarDefaults.pinnedScrollBehavior(
                rememberTopAppBarState()
            ).nestedScrollConnection)
    ) {
        items(heroesList.value.size) {
            Log.d("MyLog","Exec $it")
            HeroCard(
                heroesList.value[it].title,
                heroesList.value[it].imageUrl,
                false,{})
        }
    }

}
