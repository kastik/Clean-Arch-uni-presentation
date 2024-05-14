package com.learningwithmanos.uniexercise.heroes.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.learningwithmanos.uniexercise.heroes.data.Hero
import com.learningwithmanos.uniexercise.heroes.usecase.GetHeroesUC
import com.learningwithmanos.uniexercise.heroes.usecase.setDescriptionUC
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HeroesViewModel @Inject constructor(
    private val getHeroesUC: GetHeroesUC,
) : ViewModel() {
    var heroesStateFlow: Flow<List<HeroTileModel>> = flowOf(listOf());

    init {
        viewModelScope.launch {
            heroesStateFlow = getHeroesUC.execute().map { list -> list.map { it.mapHeroToHeroTileModel() }}
        }
    }




}

data class HeroTileModel(
    val title: String,
    val imageUrl: String,
    val id: Int
)

fun Hero.mapHeroToHeroTileModel(): HeroTileModel {
    return HeroTileModel(
        title = "$name, comics - $availableComics",
        imageUrl = imageUrl,
        id= id
    )
}