package com.learningwithmanos.uniexercise.heroes.source.remote

import com.learningwithmanos.uniexercise.heroes.data.Hero
import javax.inject.Inject

/**
 * Interface that wraps the framework that is used for the Rest calls
 */
interface HeroRemoteSource {

    /**
     * @return retrieves the a list of heroes from a certain endpoint
     */
    suspend fun getHeroes(name: String? = null): List<Hero>

}

class HeroRemoteSourceImpl @Inject constructor(
    private val restFramework: RestFramework,
): HeroRemoteSource {

    override suspend fun getHeroes(name: String?): List<Hero> {
        val response: RestApiResponse = restFramework.getData(name)

        val hero: List<Hero> =
            if (response.code == 200) {
                response.data.results.map {
                    it.toHero()
                }
            } else {
                listOf()
            }



        return hero
    }
}

