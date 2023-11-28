package com.learningwithmanos.uniexercise.heroes.source.remote

import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock

class HeroRemoteSourceImplTest {

    private lateinit var heroRemoteSourceImpl: HeroRemoteSourceImpl

    private val restFrameworkWrapperMock: DummyRestFrameworkWrapper = mock()

    @Before
    fun setUp() {
        heroRemoteSourceImpl = HeroRemoteSourceImpl(
            restFrameworkWrapperMock
        )
    }

    @Test
    fun `when invoking getHeroes verify results and interactions`() {
        // TODO
    }
}

