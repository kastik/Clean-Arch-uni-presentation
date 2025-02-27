package com.learningwithmanos.uniexercise.heroes.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "MarvelTable")
data class Hero(
    @PrimaryKey
    @ColumnInfo(name = "hero_id")
    var id: Int,

    @ColumnInfo(name = "marvel_name")
    var name: String,

    @ColumnInfo(name = "marvel_comics")
    var availableComics: Int,

    @ColumnInfo(name = "marvel_image")
    var imageUrl: String,

    @ColumnInfo(name = "marvel_desc")
    var description: String
)