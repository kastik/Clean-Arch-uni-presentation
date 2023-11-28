package com.learningwithmanos.uniexercise.heroes.repo

import com.learningwithmanos.uniexercise.heroes.data.Hero
import com.learningwithmanos.uniexercise.heroes.source.local.HeroLocalSource
import com.learningwithmanos.uniexercise.heroes.source.remote.HeroRemoteSource
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock

class HeroRepositoryImplTest {

    private lateinit var heroRepositoryImpl: HeroRepositoryImpl

    private val heroRemoteSourceMock: HeroRemoteSource = mock()
    private val heroLocalSourceMock: HeroLocalSource = mock()

    private val dummyHeroData = listOf(
        Hero(
            name = "A-Bomb (HAS)",
            availableComics = 4,
            imageUrl = "http://i.annihil.us/u/prod/marvel/i/mg/3/20/5232158de5b16.jpg",
        ),
        Hero(
            name = "Absorbing Man",
            availableComics = 99,
            imageUrl = "http://i.annihil.us/u/prod/marvel/i/mg/1/b0/5269678709fb7.jpg",
        )
    )

    @Before
    fun setUp() {
        heroRepositoryImpl = HeroRepositoryImpl(
            heroRemoteSourceMock,
            heroLocalSourceMock
        )
    }

    @Test
    fun `given no data are stored when getHeroes is invoked then verify api call and store to DB`() {
        // TODO
    }

    @Test
    fun `given data are stored when getHeroes is invoked then verify retrieving of data from DB`() {
        // TODO
    }

}