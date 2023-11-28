package com.learningwithmanos.uniexercise.heroes.source.local

import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock

class HeroLocalSourceImplTest {

    private lateinit var heroLocalSourceImpl: HeroLocalSourceImpl

    private val dbWrapperMock: DBWrapper = mock()


    @Before
    fun setUp() {
        heroLocalSourceImpl = HeroLocalSourceImpl(
            dbWrapperMock
        )
    }

    @Test
    fun `when invoking isHeroDataStored verify results and interactions`() {
        // TODO
    }

    @Test
    fun `when invoking storeHeroes verify results and interactions`() {
        // TODO
    }

    @Test
    fun `when invoking getHeroes verify results and interactions`() {
        // TODO
    }

}
