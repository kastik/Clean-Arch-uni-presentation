package com.learningwithmanos.uniexercise.heroes.usecase

import androidx.lifecycle.viewModelScope
import com.learningwithmanos.uniexercise.heroes.data.Hero
import com.learningwithmanos.uniexercise.heroes.repo.HeroRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

interface QueryUC {
    suspend fun execute(): Flow<List<Hero>>

    suspend fun update(id: Int, description: String?)

}

class QueryUSImpl @Inject constructor(
    private val heroRepository: HeroRepository
) : QueryUC {
    override suspend fun execute(): Flow<List<Hero>> {
        return heroRepository.getHeroes()
    }

    override suspend fun update(id: Int, description: String?) {
            heroRepository.update(id, description)
    }

}