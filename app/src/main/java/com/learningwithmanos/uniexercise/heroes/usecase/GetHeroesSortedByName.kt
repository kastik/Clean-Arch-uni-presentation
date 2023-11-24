package com.learningwithmanos.uniexercise.heroes.usecase

import com.learningwithmanos.uniexercise.heroes.data.Hero
import com.learningwithmanos.uniexercise.heroes.repo.HeroRepository
import javax.inject.Inject

/**
 * UC used to retrieve a list of heroes sorted by the name of heroes
 */
interface GetHeroesSortedByNameUC {
    fun execute(): List<Hero>
}

class GetHeroesSortedByNameUCImpl @Inject constructor(
    private val heroRepository: HeroRepository
): GetHeroesSortedByNameUC {
    override fun execute(): List<Hero> {
        return heroRepository.getHeroes().sortedBy { it.name }
    }

}