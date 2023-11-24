package com.learningwithmanos.uniexercise.heroes.ui

import com.learningwithmanos.uniexercise.heroes.data.Tab
import com.learningwithmanos.uniexercise.heroes.usecase.GetHeroesSortedByHighestNumberOfComicsUC
import com.learningwithmanos.uniexercise.heroes.usecase.GetHeroesSortedByNameUC
import com.learningwithmanos.uniexercise.heroes.usecase.GetHeroesUC
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.verifyNoMoreInteractions


class HeroesViewModelTest {

    private lateinit var heroesViewModel: HeroesViewModel

    private val getHeroesUCMock: GetHeroesUC = mock()
    private val getHeroesSortedByNameUCMock: GetHeroesSortedByNameUC = mock()
    private val getHeroesSortedByHighestNumberOfComicsUCMock: GetHeroesSortedByHighestNumberOfComicsUC = mock()

    @Before
    fun setUp() {
        heroesViewModel = HeroesViewModel(
            getHeroesUCMock,
            getHeroesSortedByNameUCMock,
            getHeroesSortedByHighestNumberOfComicsUCMock
        )
    }

    @Test
    fun `when getSelectedIndex is invoked with parameter then verify results`() {
        // when
        val actualResult1 = heroesViewModel.getSelectedIndex(Tab.Heroes)
        val actualResult2 = heroesViewModel.getSelectedIndex(Tab.SortedByNameHeroes)
        val actualResult3 = heroesViewModel.getSelectedIndex(Tab.SortedByComicHeroes)


        // then
        assertThat(actualResult1, equalTo(0))
        assertThat(actualResult2, equalTo(1))
        assertThat(actualResult3, equalTo(2))
    }

    @Test
    fun `when getHeroes is invoked with parameter then verify results`() {
        // when
        heroesViewModel.getHeroes(Tab.Heroes)
        heroesViewModel.getHeroes(Tab.SortedByNameHeroes)
        heroesViewModel.getHeroes(Tab.SortedByComicHeroes)


        // then
        verify(getHeroesUCMock).execute()
        verifyNoMoreInteractions(getHeroesUCMock)
        verify(getHeroesSortedByNameUCMock).execute()
        verifyNoMoreInteractions(getHeroesSortedByNameUCMock)
        verify(getHeroesSortedByHighestNumberOfComicsUCMock).execute()
        verifyNoMoreInteractions(getHeroesSortedByHighestNumberOfComicsUCMock)
    }

}
