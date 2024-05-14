package com.learningwithmanos.uniexercise.heroes.usecase

import com.learningwithmanos.uniexercise.heroes.repo.HeroRepository
import java.security.PrivateKey
import javax.inject.Inject

interface SettingsScreenUC {
    suspend fun updateApi(apiKey: String, privateKey: String)
}

class SettingsScreenUCImpl @Inject constructor(
    private val heroRepository: HeroRepository
) : SettingsScreenUC {
    override suspend fun updateApi(apiKey: String, privateKey: String) {
        heroRepository.updateApi(apiKey, privateKey)
    }
}