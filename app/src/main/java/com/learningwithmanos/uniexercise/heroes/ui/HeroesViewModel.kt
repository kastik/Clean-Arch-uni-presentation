package com.learningwithmanos.uniexercise.heroes.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.learningwithmanos.uniexercise.heroes.data.Hero
import com.learningwithmanos.uniexercise.heroes.data.Tab
import com.learningwithmanos.uniexercise.heroes.usecase.GetHeroesSortedByHighestNumberOfComicsUC
import com.learningwithmanos.uniexercise.heroes.usecase.GetHeroesSortedByNameUC
import com.learningwithmanos.uniexercise.heroes.usecase.GetHeroesUC
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class HeroesViewModel @Inject constructor(
    private val getHeroesUC: GetHeroesUC,
    private val getHeroesSortedByNameUC: GetHeroesSortedByNameUC,
    private val getHeroesSortedByHighestNumberOfComicsUC: GetHeroesSortedByHighestNumberOfComicsUC
) : ViewModel() {

    private var _selectedTabStateFlow: MutableStateFlow<Tab> = MutableStateFlow(Tab.Heroes)

    val selectedTabStateFlow: StateFlow<Tab> = _selectedTabStateFlow.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = _selectedTabStateFlow.value
    )

    /**
     * Utilises corresponding UC to retrieve data based on the selectedTab.
     * @param selectedTab
     */
    fun getHeroes(selectedTab: Tab): List<HeroTileModel> {
        return when (selectedTab) {
            Tab.Heroes -> getHeroesUC.execute().map { it.mapHeroToHeroTileModel() }
            Tab.SortedByNameHeroes -> getHeroesSortedByNameUC.execute()
                .map { it.mapHeroToHeroTileModel() }

            Tab.SortedByComicHeroes -> getHeroesSortedByHighestNumberOfComicsUC.execute()
                .map { it.mapHeroToHeroTileModel() }
        }
    }

    /**
     * Utilises corresponding UC to retrieve data based on the selectedTab.
     * @param selectedTab
     */
    fun getSelectedIndex(selectedTab: Tab): Int {
        return when (selectedTab) {
            Tab.Heroes -> 0
            Tab.SortedByNameHeroes -> 1
            Tab.SortedByComicHeroes -> 2
        }
    }

    /**
     * Sets the selected tab
     */
    fun selectTab(tab: Tab) {
        _selectedTabStateFlow.value = tab
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