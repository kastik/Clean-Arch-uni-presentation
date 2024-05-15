package com.learningwithmanos.uniexercise.heroes.ui.QueryScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.learningwithmanos.uniexercise.heroes.data.Hero
import com.learningwithmanos.uniexercise.heroes.ui.HeroTileModel
import com.learningwithmanos.uniexercise.heroes.ui.mapHeroToHeroTileModel
import com.learningwithmanos.uniexercise.heroes.usecase.QueryUC
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class QueryViewModel @Inject constructor(
private val getQueryUC: QueryUC,
) : ViewModel() {
    private val _heroesStateFlow = MutableStateFlow<List<Hero>>(emptyList())
    val heroesStateFlow: StateFlow<List<Hero>> = _heroesStateFlow

    init {
        viewModelScope.launch {
            getQueryUC.execute().collect { list ->
                _heroesStateFlow.value = list
            }
        }
    }

    fun storeHero(hero: Hero) {
        viewModelScope.launch {
            getQueryUC.storeLocal(hero)
        }
    }
}