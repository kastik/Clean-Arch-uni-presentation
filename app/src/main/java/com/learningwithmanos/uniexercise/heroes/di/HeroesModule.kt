package com.learningwithmanos.uniexercise.heroes.di

import com.learningwithmanos.uniexercise.heroes.repo.HeroRepository
import com.learningwithmanos.uniexercise.heroes.repo.HeroRepositoryImpl
import com.learningwithmanos.uniexercise.heroes.source.local.HeroLocalSource
import com.learningwithmanos.uniexercise.heroes.source.local.HeroLocalSourceImpl
import com.learningwithmanos.uniexercise.heroes.source.remote.HeroRemoteSource
import com.learningwithmanos.uniexercise.heroes.source.remote.HeroRemoteSourceImpl
import com.learningwithmanos.uniexercise.heroes.source.remote.RestFramework
import com.learningwithmanos.uniexercise.heroes.source.remote.RestFrameworkImpl
import com.learningwithmanos.uniexercise.heroes.usecase.GetHeroesUC
import com.learningwithmanos.uniexercise.heroes.usecase.GetHeroesUCImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface HeroesModule {

    // Usecase

    @Binds
    fun bindsGetHeroesUC(
        getHeroesUCImpl: GetHeroesUCImpl
    ): GetHeroesUC

    // Repository

    @Binds
    fun bindsHeroRepository(
        heroRepositoryImpl: HeroRepositoryImpl
    ): HeroRepository

    // Source

    @Binds
    fun bindsHeroLocalSource(
        heroLocalSourceImpl: HeroLocalSourceImpl
    ): HeroLocalSource

    @Binds
    fun bindsHeroRemoteSource(
        heroRemoteSourceImpl: HeroRemoteSourceImpl
    ): HeroRemoteSource

    // external frameworks

    @Binds
    fun bindMarvelRepo(
        marvelRepoImpl: RestFrameworkImpl
    ): RestFramework
}