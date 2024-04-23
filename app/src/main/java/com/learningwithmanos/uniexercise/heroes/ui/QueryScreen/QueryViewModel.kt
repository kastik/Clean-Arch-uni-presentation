package com.learningwithmanos.uniexercise.heroes.ui.QueryScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.learningwithmanos.uniexercise.heroes.data.Hero
import com.learningwithmanos.uniexercise.heroes.ui.HeroTileModel
import com.learningwithmanos.uniexercise.heroes.ui.mapHeroToHeroTileModel
import com.learningwithmanos.uniexercise.heroes.usecase.GetHeroesUC
import com.learningwithmanos.uniexercise.heroes.usecase.QueryUC
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class QueryViewModel @Inject constructor(
private val getQueryUC: QueryUC,
) : ViewModel() {
    var heroesStateFlow: Flow<List<HeroTileModel>> = flowOf(listOf());
    init {
        viewModelScope.launch {

            heroesStateFlow = getQueryUC.execute().map { list -> list.map { it.mapHeroToHeroTileModel() }}

            suspend fun setDesk(id: Int) {
                getQueryUC.setDesc(id)
            }
        }
    }




}

data class HeroTileModel(
    val title: String,
    val imageUrl: String,
)

fun Hero.mapHeroToHeroTileModel(): HeroTileModel {
    return HeroTileModel(
        title = "$name, comics - $availableComics",
        imageUrl = imageUrl
    )
}