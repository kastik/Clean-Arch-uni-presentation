package com.learningwithmanos.uniexercise.heroes.usecase

import com.learningwithmanos.uniexercise.heroes.data.Hero
import com.learningwithmanos.uniexercise.heroes.repo.HeroRepository
import javax.inject.Inject

/**
 * UC used to retrieve a list of heroes sorted by the name of heroes
 */
interface GetHeroesSortedByHighestNumberOfComicsUC {
    fun execute(): List<Hero>
}

class GetHeroesSortedByHighestNumberOfComicsUCImpl @Inject constructor(
    private val heroRepository: HeroRepository,
): GetHeroesSortedByHighestNumberOfComicsUC {
    override fun execute(): List<Hero> {
        // TODO
        return listOf()
    }
}