package com.learningwithmanos.uniexercise.heroes.repo

import com.learningwithmanos.uniexercise.heroes.data.Hero
import com.learningwithmanos.uniexercise.heroes.source.local.HeroLocalSource
import com.learningwithmanos.uniexercise.heroes.source.remote.HeroRemoteSource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

/**
 * A repository interface that is used to coordinate the usage of the LocalSource and the
 * RemoteSource.
 */
interface HeroRepository {

    /**
     * In the scope of this method it is decided from which source the data should be retrieved from.
     * Retrieve from local if data are stored, otherwise retrieve data from remote and also store
     * the data to the local.
     *
     * @return list of heroes
     */
    //suspend fun getHeroes(): Flow<List<Hero>>

    suspend fun getStoredHeroes(): Flow<List<Hero>>

    suspend fun getOnlineHeroes(name: String?=null): Flow<List<Hero>>

    suspend fun storeLocalHero(hero: Hero)
    suspend fun updateApi(apikey: String, privatekey: String)
}

class HeroRepositoryImpl @Inject constructor(
    private val heroRemoteSource: HeroRemoteSource,
    private val heroLocalSource: HeroLocalSource,
) : HeroRepository {

    /***TODO Question 4 uxma

    Query can't be cached bc it's semi-random user input and 1000+ entries,
    hero's tab has only locally stored dada

    what was this for?

    @OptIn(ExperimentalCoroutinesApi::class)
    override suspend fun getHeroes(): Flow<List<Hero>>  {
        return heroLocalSource.isEmpty().flatMapLatest { isEmpty ->
            if (isEmpty) {
                val heroList = heroRemoteSource.getHeroes()
                heroLocalSource.storeHeroes(heroList)
                flowOf((heroList))
            } else {
                heroLocalSource.getHeroes()
            }
        }
    }
     ***/

    override suspend fun getStoredHeroes(): Flow<List<Hero>> {
        return heroLocalSource.getHeroes()
    }

    override suspend fun getOnlineHeroes(name: String?): Flow<List<Hero>> {
        return flowOf(heroRemoteSource.getHeroes(name))
    }

    override suspend fun storeLocalHero(hero: Hero) {
        heroLocalSource.storeHero(hero)
    }

    override suspend fun updateApi(apikey: String, privatekey: String) {
        heroLocalSource.updateApi(apikey, privatekey)
    }


}