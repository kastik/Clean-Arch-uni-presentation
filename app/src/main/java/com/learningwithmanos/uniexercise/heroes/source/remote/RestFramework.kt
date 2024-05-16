package com.learningwithmanos.uniexercise.heroes.source.remote

import com.learningwithmanos.uniexercise.heroes.network.MarvelApi
import com.learningwithmanos.uniexercise.heroes.network.MarvelApiClient
import com.learningwithmanos.uniexercise.utils.MarvelRequestGenerator
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

private var client: MarvelApi = MarvelApiClient.api
interface RestFramework{
    suspend fun getData(name:String?): RestApiResponse
    suspend fun getParams(): Flow<MarvelRequestGenerator>
}

class RestFrameworkImpl @Inject constructor(): RestFramework {
    override suspend fun getData(name: String?): RestApiResponse {
        val params = MarvelRequestGenerator.createParams()
        return client.getCharacters(
            params.timestamp,
            params.apiKey,
            params.hash,
            20,
            0,
            name.let { if (it=="") null else it  }
        )
    }

    override suspend fun getParams(): Flow<MarvelRequestGenerator> {
        return flowOf(MarvelRequestGenerator.createParams())
    }
}