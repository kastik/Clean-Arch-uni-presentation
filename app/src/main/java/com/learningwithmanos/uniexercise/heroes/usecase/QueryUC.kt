package com.learningwithmanos.uniexercise.heroes.usecase

import com.learningwithmanos.uniexercise.heroes.data.Hero
import com.learningwithmanos.uniexercise.heroes.repo.HeroRepository
import com.learningwithmanos.uniexercise.heroes.source.remote.HeroRemoteSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

interface QueryUC {
    suspend fun execute(name: String? = null): Flow<List<Hero>>

    suspend fun storeLocal(hero: Hero) //TODO THIS IS FOR A QUICK TEST
}

class QueryUCImpl @Inject constructor(
    private val heroRepository: HeroRepository
) : QueryUC {

    override suspend fun execute(name: String?): Flow<List<Hero>> {
        return heroRepository.getOnlineHeroes(name)
    }

    override suspend fun storeLocal(hero: Hero) {
        heroRepository.storeLocalHero(hero)
    }

}