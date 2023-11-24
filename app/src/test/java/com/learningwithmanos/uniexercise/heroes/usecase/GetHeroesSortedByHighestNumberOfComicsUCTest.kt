package com.learningwithmanos.uniexercise.heroes.usecase

import com.learningwithmanos.uniexercise.heroes.data.Hero
import com.learningwithmanos.uniexercise.heroes.repo.HeroRepository
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import org.mockito.BDDMockito.given
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify


class GetHeroesSortedByHighestNumberOfComicsUCImplTest {

    private lateinit var getHeroesSortedByHighestNumberOfComicsUCImpl: GetHeroesSortedByHighestNumberOfComicsUCImpl

    private val heroRepositoryMock: HeroRepository = mock()

    @Before
    fun setUp() {
        getHeroesSortedByHighestNumberOfComicsUCImpl = GetHeroesSortedByHighestNumberOfComicsUCImpl(
            heroRepositoryMock
        )
    }

    @Test
    fun `when execute is invoked then verify interactions`(){
        // given
        val heroesList = dummyHeroList()
        given(heroRepositoryMock.getHeroes()).willReturn(heroesList)
        val expectedHeroesList = sortHeroListByComicsNumber(heroesList)

        // when
        val actual = getHeroesSortedByHighestNumberOfComicsUCImpl.execute()

        // then
        assertThat(actual, equalTo(expectedHeroesList))
        verify(heroRepositoryMock).getHeroes()
    }

    private fun dummyHeroList(): List<Hero> {
        return listOf(
            Hero(
                name = "Absorbing Man",
                availableComics = 99,
                imageUrl = "http://i.annihil.us/u/prod/marvel/i/mg/1/b0/5269678709fb7.jpg",
            ),
            Hero(
                name = "3-D Man",
                availableComics = 12,
                imageUrl = "http://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784.jpg",
            ),
            Hero(
                name = "Aaron Stack",
                availableComics = 14,
                imageUrl = "http://i.annihil.us/u/prod/marvel/i/mg/b/40/image_not_available.jpg",
            ),
            Hero(
                name = "A.I.M.",
                availableComics = 53,
                imageUrl = "http://i.annihil.us/u/prod/marvel/i/mg/6/20/52602f21f29ec.jpg",
            )
        )
    }

    private fun sortHeroListByComicsNumber(heroesList: List<Hero>): List<Hero> {
        return heroesList.sortedByDescending { it.availableComics }
    }

}