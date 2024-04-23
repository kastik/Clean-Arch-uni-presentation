package com.learningwithmanos.uniexercise.heroes.data

data class Hero(
    var id: Int,
    val name: String,
    val availableComics: Int,
    val imageUrl: String,
    val description: String
)