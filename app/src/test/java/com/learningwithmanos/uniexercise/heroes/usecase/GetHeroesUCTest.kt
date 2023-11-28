package com.learningwithmanos.uniexercise.heroes.usecase

import com.learningwithmanos.uniexercise.heroes.repo.HeroRepository
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock

class GetHeroesUCImplTest {

    private lateinit var getHeroesUCImpl: GetHeroesUCImpl

    private val heroRepositoryMock: HeroRepository = mock()


    @Before
    fun setUp() {
        getHeroesUCImpl = GetHeroesUCImpl(
            heroRepositoryMock
        )
    }

    @Test
    fun `when execute is invoked then verify interactions`() {
        // TODO
    }

}