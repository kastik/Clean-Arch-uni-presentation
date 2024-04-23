package com.learningwithmanos.uniexercise.network

import com.learningwithmanos.uniexercise.heroes.source.remote.RestApiResponse
import retrofit2.http.GET
import retrofit2.http.Query


interface MarvelApi {

    @GET("characters")
    suspend fun getCharacters(
        @Query("ts") timestamp: Long?,
        @Query("apikey") apiKey: String?,
        @Query("hash") hash: String?,
        @Query("limit") limit: Int?,
        @Query("offset") offset: Int?
    ): RestApiResponse
}
