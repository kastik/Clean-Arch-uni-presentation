package com.learningwithmanos.uniexercise.heroes.source.remote

import com.learningwithmanos.uniexercise.heroes.data.HeroData
import com.learningwithmanos.uniexercise.network.MarvelApi
import com.learningwithmanos.uniexercise.network.MarvelApiClient
import com.learningwithmanos.uniexercise.utils.MarvelRequestGenerator
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

private var client: MarvelApi = MarvelApiClient.api
interface RestFramework{
    suspend fun getData(): RestApiResponse
    suspend fun getParams(): Flow<MarvelRequestGenerator>
}

class RestFrameworkImpl @Inject constructor(): RestFramework {
    override suspend fun getData(): RestApiResponse {
        var response = RestApiResponse(0, "", HeroData(listOf()))
        try {
            val params = MarvelRequestGenerator.createParams()
            response = client.getCharacters(
                params.timestamp,
                params.apiKey,
                params.hash,
                20,
                0
            )
        } catch (_: Exception) {
            response.data = HeroData(listOf())
        }

        return response
    }

    override suspend fun getParams(): Flow<MarvelRequestGenerator> {
        return flowOf(MarvelRequestGenerator.createParams())
    }
}