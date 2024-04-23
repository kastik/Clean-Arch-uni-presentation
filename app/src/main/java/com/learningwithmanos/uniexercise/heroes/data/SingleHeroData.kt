package com.learningwithmanos.uniexercise.heroes.data

import com.google.gson.annotations.SerializedName

data class SingleHeroData(
    @SerializedName("results")
    val results: SHero
)
