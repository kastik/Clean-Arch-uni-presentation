package com.learningwithmanos.uniexercise.ui.QuizScreen

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
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
    //var heroesStateFlow: StateFlow<List<Hero>> = _heroesStateFlow
    var randHero: MutableState<Hero?> = mutableStateOf(null)

    init {
        viewModelScope.launch {
            quizUC.execute().collect{ heroes ->
                _heroesStateFlow.value = heroes
                Log.d("QuizViewModel", "Heroes: ${heroes.size}")
                randHero.value = getRandomHero()
            }
        }
    }

    fun getRandomHero(): Hero? {
        return if (_heroesStateFlow.value.isNotEmpty()) {
            _heroesStateFlow.value[Random.nextInt((_heroesStateFlow.value.size-1))]
        } else {
            Log.d("QuizViewModel", "No heroes")
            null
        }
    }
}