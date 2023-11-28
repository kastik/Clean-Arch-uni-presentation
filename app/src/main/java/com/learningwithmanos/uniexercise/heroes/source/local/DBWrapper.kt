package com.learningwithmanos.uniexercise.heroes.source.local

import com.learningwithmanos.uniexercise.heroes.data.Hero
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

interface DBWrapper {
    suspend fun isHeroDataStored(): Flow<Boolean>
    suspend fun storeHeroes(heroes: List<Hero>)
    suspend fun getHeroes(): Flow<List<Hero>>
}

class DummyDBWrapper @Inject constructor() : DBWrapper {
    override suspend fun isHeroDataStored(): Flow<Boolean> {
        return flowOf(false)
    }

    override suspend fun storeHeroes(heroes: List<Hero>) {
        // do nothing
    }

    override suspend fun getHeroes(): Flow<List<Hero>> {
        return flowOf(listOf())
    }
}