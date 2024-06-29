package com.learningwithmanos.uniexercise.heroes.usecase

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.learningwithmanos.uniexercise.heroes.data.Hero
import com.learningwithmanos.uniexercise.heroes.repo.HeroRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

interface QuizHeroesUC {
    suspend fun execute(): Flow<List<Hero>>

}

class QuizHeroesImpl @Inject constructor(
    private val heroRepository: HeroRepository
) : QuizHeroesUC {
    override suspend fun execute(): Flow<List<Hero>> {
        Log.d("MyLog","QuizHeroesImpl")
        return heroRepository.getStoredHeroes()
    }

}