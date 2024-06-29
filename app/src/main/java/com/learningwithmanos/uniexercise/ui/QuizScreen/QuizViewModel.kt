package com.learningwithmanos.uniexercise.ui.QuizScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.learningwithmanos.uniexercise.heroes.data.Hero
import com.learningwithmanos.uniexercise.heroes.usecase.QuizHeroesImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class QuizViewModel @Inject constructor(
    private val quizUC: QuizHeroesImpl
) : ViewModel() {
    private val _heroesStateFlow: MutableStateFlow<List<Hero>> = MutableStateFlow(emptyList())
    var heroesStateFlow: StateFlow<List<Hero>> = _heroesStateFlow

    init {
        viewModelScope.launch {
            quizUC.execute().collect{ heroes ->
                _heroesStateFlow.value = heroes
            }
        }
    }

    fun getRandomHero(): Hero? {
        val heroes = _heroesStateFlow.value
        return if (heroes.isNotEmpty()) {
            heroes[Random.nextInt(heroes.size)]
        } else {
            null
        }
    }
}