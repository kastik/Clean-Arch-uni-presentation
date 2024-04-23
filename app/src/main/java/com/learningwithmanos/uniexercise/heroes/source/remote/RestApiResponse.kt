package com.learningwithmanos.uniexercise.heroes.source.remote

import com.google.gson.annotations.SerializedName
import com.learningwithmanos.uniexercise.heroes.data.HeroData

data class RestApiResponse(
    @SerializedName("code")
    var code: Int,

    @SerializedName("status")
    var status: String,

    @SerializedName("data")
    var data: HeroData
)