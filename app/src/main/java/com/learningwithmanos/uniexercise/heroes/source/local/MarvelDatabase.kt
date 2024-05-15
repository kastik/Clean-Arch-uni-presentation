package com.learningwithmanos.uniexercise.heroes.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.learningwithmanos.uniexercise.heroes.data.Hero


@Database(entities = [Hero::class], version = 3, exportSchema = false)
@TypeConverters(Converters::class)
abstract class MarvelDatabase: RoomDatabase() {
    abstract fun marvelDao(): MarvelDao

}