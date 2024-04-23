package com.learningwithmanos.uniexercise.heroes.usecase

import android.util.Log
import com.learningwithmanos.uniexercise.heroes.data.Hero
import com.learningwithmanos.uniexercise.heroes.repo.HeroRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * UC used to retrieve a list of heroes
 */
interface GetHeroesUC {
    suspend fun execute(): Flow<List<Hero>>

}

class GetHeroesUCImpl @Inject constructor(
    private val heroRepository: HeroRepository
) : GetHeroesUC {
    override suspend fun execute(): Flow<List<Hero>> {
        Log.d("MyLog","GetHeroesUCImpl EXEC")
        return heroRepository.getQuery()
    }


}