package com.learningwithmanos.uniexercise.heroes.usecase

import android.util.Log
import com.learningwithmanos.uniexercise.heroes.data.Hero
import com.learningwithmanos.uniexercise.heroes.repo.HeroRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


interface setDescriptionUC {
    suspend fun execute(id: Int)

}

class setDescriptionImpl @Inject constructor(
    private val heroRepository: HeroRepository
) : setDescriptionUC {
    override suspend fun execute(id: Int) {
        Log.d("MyLog","setHeroesDescrUCImpl EXEC")
        heroRepository.setDesc(id)
    }
}