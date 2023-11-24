package com.learningwithmanos.uniexercise.heroes.usecase

import com.learningwithmanos.uniexercise.heroes.data.Hero
import com.learningwithmanos.uniexercise.heroes.repo.HeroRepository
import javax.inject.Inject

/**
 * UC used to retrieve a list of heroes
 */
interface GetHeroesUC {
    fun execute(): List<Hero>
}

class GetHeroesUCImpl @Inject constructor(
    private val heroRepository: HeroRepository
) : GetHeroesUC {
    override fun execute(): List<Hero> {
        return heroRepository.getHeroes()
    }

}