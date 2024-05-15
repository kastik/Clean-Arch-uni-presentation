package com.learningwithmanos.uniexercise.heroes.network

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MarvelApiClient {

    private const val BASE_URL = "https://gateway.marvel.com/v1/public/"

    val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    val client = OkHttpClient.Builder().apply {
        addInterceptor(loggingInterceptor)
    }.build()

    val api: MarvelApi by lazy {
        Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .client(client)
        .build()
        .create(MarvelApi::class.java) }

    }
