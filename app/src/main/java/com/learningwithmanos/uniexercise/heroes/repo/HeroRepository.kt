package com.learningwithmanos.uniexercise.heroes.repo

import com.learningwithmanos.uniexercise.heroes.data.Hero
import com.learningwithmanos.uniexercise.heroes.source.local.HeroLocalSource
import com.learningwithmanos.uniexercise.heroes.source.remote.HeroRemoteSource
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
    fun getHeroes(): List<Hero>
}

class HeroRepositoryImpl @Inject constructor(
    private val heroRemoteSource: HeroRemoteSource,
    private val heroLocalSource: HeroLocalSource,
): HeroRepository {
    override fun getHeroes(): List<Hero> {
        return if (!heroLocalSource.isHeroDataStored()) {
            val heroes = heroRemoteSource.getHeroes()
            heroLocalSource.storeHeroes(heroes)
            heroes
        } else {
            heroLocalSource.getHeroes()
        }
    }

}