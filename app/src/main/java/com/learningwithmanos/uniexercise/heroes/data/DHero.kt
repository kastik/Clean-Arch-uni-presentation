package com.learningwithmanos.uniexercise.heroes.data

import com.google.gson.annotations.SerializedName

data class DHero(
    @SerializedName("id")
    var id: Int,
    @SerializedName("description")
    var description: String,
)
