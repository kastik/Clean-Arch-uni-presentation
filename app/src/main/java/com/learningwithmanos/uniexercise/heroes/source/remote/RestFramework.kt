package com.learningwithmanos.uniexercise.heroes.source.remote

import com.learningwithmanos.uniexercise.heroes.data.Comics
import com.learningwithmanos.uniexercise.heroes.data.HeroData
import com.learningwithmanos.uniexercise.heroes.data.SHero
import com.learningwithmanos.uniexercise.heroes.data.SingleHeroData
import com.learningwithmanos.uniexercise.heroes.data.Thumbnail
import com.learningwithmanos.uniexercise.network.MarvelApi
import com.learningwithmanos.uniexercise.network.MarvelApiClient
import com.learningwithmanos.uniexercise.utils.MarvelRequestGenerator
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

private var client: MarvelApi = MarvelApiClient.api
interface RestFramework{
    suspend fun getData(offset:Int): RestApiResponse
    suspend fun getHero(id: Int): SingleRestResponse
    suspend fun getParams(): Flow<MarvelRequestGenerator>
}

class RestFrameworkImpl @Inject constructor(): RestFramework {
    override suspend fun getData(offset:Int): RestApiResponse {
        lateinit var response: RestApiResponse
        try {
            val params = MarvelRequestGenerator.createParams()
            response = client.getCharacters(
                params.timestamp,
                params.apiKey,
                params.hash,
                100,
                offset
            )
        } catch (_: Exception) {
            response.data = HeroData(listOf())
        }

        return response
    }

    override suspend fun getHero(id: Int): SingleRestResponse {
        lateinit var response: SingleRestResponse
        try {
            val params = MarvelRequestGenerator.createParams()
            response = client.getCharacter(
                id,
                params.timestamp,
                params.apiKey,
                params.hash,
                20,
                0
            )
        } catch (_: Exception) {
            //response.data = SingleHeroData(SHero(0,"", Comics(0), Thumbnail("",""),""))
        }

        return response
    }

    override suspend fun getParams(): Flow<MarvelRequestGenerator> {
        return flowOf(MarvelRequestGenerator.createParams())
    }
}