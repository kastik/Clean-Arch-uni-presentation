package com.learningwithmanos.uniexercise.heroes.source.remote

import com.learningwithmanos.uniexercise.heroes.data.Hero
import com.learningwithmanos.uniexercise.heroes.DummyData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

interface RestFrameworkWrapper{
    suspend fun getHeroes(): Flow<List<Hero>>
}

class DummyRestFrameworkWrapper @Inject constructor(): RestFrameworkWrapper {
    override suspend fun getHeroes(): Flow<List<Hero>> {
        return flowOf(DummyData.dummyHeroList)
    }
}