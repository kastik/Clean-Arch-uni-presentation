package com.learningwithmanos.uniexercise.heroes.data

import android.util.Log
import com.google.gson.annotations.SerializedName


data class Thumbnail (
        @SerializedName("path")
        val path: String,
        @SerializedName("extension")
        val ext: String
)

data class Comics (
        @SerializedName("available")
        val availableComics: Int
)

data class HeroData (
        @SerializedName("results")
        val results: List<RHero>
)

data class RHero(
        @SerializedName("id")
        var id: Int,

        @SerializedName("name")
        var name: String,

        @SerializedName("comics")
        var availableComics: Comics,

        @SerializedName("thumbnail")
        var imageUrl: Thumbnail,

        @SerializedName("description")
        var desc: String
){
        fun toHero(): Hero {
                Log.d("MyLog","${imageUrl.path.replace("http", "https")}.${imageUrl.ext}")
                return Hero(
                        id = this.id,
                        name = this.name,
                        availableComics = this.availableComics.availableComics,
                        imageUrl = "${imageUrl.path.replace("http", "https")}.${imageUrl.ext}",
                        description = this.desc
                )
        }
}