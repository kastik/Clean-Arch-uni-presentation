package com.learningwithmanos.uniexercise.heroes.network

import com.learningwithmanos.uniexercise.heroes.source.remote.RestApiResponse
import com.learningwithmanos.uniexercise.heroes.source.remote.SingleRestResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarvelApi {

    @GET("characters")
    suspend fun getCharacters(
        @Query("ts") timestamp: Long?,
        @Query("apikey") apiKey: String?,
        @Query("hash") hash: String?,
        @Query("limit") limit: Int?,
        @Query("offset") offset: Int?,
        @Query("nameStartsWith") nameStartsWith: String?
    ): RestApiResponse

    @GET("characters/{id}")
    suspend fun getCharacter(
        @Path("id") id: Int,
        @Query("ts") timestamp: Long?,
        @Query("apikey") apiKey: String?,
        @Query("hash") hash: String?,
        @Query("limit") limit: Int?,
        @Query("offset") offset: Int?
    ): SingleRestResponse
}
