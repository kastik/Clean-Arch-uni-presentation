package com.learningwithmanos.uniexercise.heroes.source.remote

import com.learningwithmanos.uniexercise.heroes.data.Hero
import com.learningwithmanos.uniexercise.heroes.data.RHero
import com.learningwithmanos.uniexercise.heroes.source.local.Converters
import javax.inject.Inject

/**
 * Interface that wraps the framework that is used for the Rest calls
 */
interface HeroRemoteSource {

    /**
     * @return retrieves the a list of heroes from a certain endpoint
     */
    suspend fun getHeroes(): List<Hero>
    suspend fun getDesc(id: Int): Hero
}

class HeroRemoteSourceImpl @Inject constructor(
    private val restFramework: RestFramework,
): HeroRemoteSource {

    override suspend fun getHeroes(): List<Hero> {
        val response: RestApiResponse = restFramework.getData()

        val hero: List<Hero> = if (response.code == 200) {
            response.data.results.map {
                it.mapToHero()
            }
        } else {
            listOf()
        }

        return hero
    }

    override suspend fun getDesc(id: Int): Hero {
        val response: SingleRestResponse = restFramework.getHero(id)
        val hero: Hero = if (response.code == 200) {
            Hero(response.data.results.id, response.data.results.name, response.data.results.availableComics.availableComics, Converters().thumbnailToString(response.data.results.imageUrl), response.data.results.desc, )
    } else {
            Hero(0,"",0,"","")
        }

        return hero
    }

    private fun RHero.mapToHero() = Hero (
        id = this.id,
        name = this.name,
        availableComics = this.availableComics.availableComics,
        imageUrl = Converters().thumbnailToString(this.imageUrl),
        description = ""
    )

}

