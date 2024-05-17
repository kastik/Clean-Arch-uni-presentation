package com.learningwithmanos.uniexercise.heroes.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.learningwithmanos.uniexercise.heroes.data.Hero
import com.learningwithmanos.uniexercise.heroes.usecase.GetHeroesUC
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HeroesViewModel @Inject constructor(
    private val getHeroesUC: GetHeroesUC,
) : ViewModel() {
    var heroesStateFlow: Flow<List<Hero>> = flowOf(listOf())

    private var _heroDetailsUIState = MutableStateFlow(HeroDetailsWrapper())
    val heroDetailsUIState: StateFlow<HeroDetailsWrapper> = _heroDetailsUIState.asStateFlow()


    init {
        viewModelScope.launch {
            heroesStateFlow = getHeroesUC.execute()
        }
    }


    fun showHeroDetails(hero: Hero){
        _heroDetailsUIState.update {
            it.copy(
                selectedHero = hero,
                showPopup = true
            )
        }
    }

    fun dismissHeroDetails(){
        _heroDetailsUIState.update {
            it.copy(
                showPopup = false
            )
        }
    }

}


data class HeroDetailsWrapper(
    val selectedHero: Hero? =null,
    val showPopup: Boolean = false
)