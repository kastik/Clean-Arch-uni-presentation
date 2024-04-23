package com.learningwithmanos.uniexercise.heroes.usecase

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.learningwithmanos.uniexercise.heroes.data.Hero
import com.learningwithmanos.uniexercise.heroes.repo.HeroRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

interface QueryUC {
    suspend fun execute(): Flow<List<Hero>>

    suspend fun setDesc(id: Int)

}

class QueryUSImpl @Inject constructor(
    private val heroRepository: HeroRepository
) : QueryUC {
    override suspend fun execute(): Flow<List<Hero>> {
        Log.d("MyLog","QueryUSImpl")
        return heroRepository.getHeroes()
    }

    override suspend fun setDesc(id: Int) {
            heroRepository.setDesc(id)
    }

}