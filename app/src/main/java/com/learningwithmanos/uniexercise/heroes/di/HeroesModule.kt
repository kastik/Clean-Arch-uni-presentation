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
import com.learningwithmanos.uniexercise.heroes.usecase.QueryUC
import com.learningwithmanos.uniexercise.heroes.usecase.QueryUSImpl
import com.learningwithmanos.uniexercise.heroes.usecase.QuizHeroesImpl
import com.learningwithmanos.uniexercise.heroes.usecase.QuizHeroesUC
import com.learningwithmanos.uniexercise.heroes.usecase.SettingsScreenUC
import com.learningwithmanos.uniexercise.heroes.usecase.SettingsScreenUCImpl
import com.learningwithmanos.uniexercise.heroes.usecase.setDescriptionImpl
import com.learningwithmanos.uniexercise.heroes.usecase.setDescriptionUC
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

    @Binds
    fun bindsQueryUC(
        queryUCImpl: QueryUSImpl
    ): QueryUC

    @Binds
    fun bindsgetDescriptionUC(
        getDescriptionUcImpl: setDescriptionImpl
    ): setDescriptionUC

    @Binds
    fun bindsSettingsScreenUC(
        settingApi: SettingsScreenUCImpl
    ): SettingsScreenUC

    @Binds
    fun bindsQuizHeroesUC(
        quizHeroesUC: QuizHeroesImpl
    ): QuizHeroesUC

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