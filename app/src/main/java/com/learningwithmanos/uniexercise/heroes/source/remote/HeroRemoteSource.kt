package com.learningwithmanos.uniexercise.heroes.source.remote

import com.learningwithmanos.uniexercise.heroes.data.Hero
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Interface that wraps the framework that is used for the Rest calls
 */
interface HeroRemoteSource {

    /**
     * @return retrieves the a list of heroes from a certain endpoint
     */
    suspend fun getHeroes(): Flow<List<Hero>>
}

class HeroRemoteSourceImpl @Inject constructor(
    private val restFrameworkWrapper: DummyRestFrameworkWrapper,
): HeroRemoteSource {

    override suspend fun getHeroes(): Flow<List<Hero>> {
        return restFrameworkWrapper.getHeroes()
    }

}

