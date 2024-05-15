package com.learningwithmanos.uniexercise.heroes.source.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.learningwithmanos.uniexercise.heroes.data.Hero
import kotlinx.coroutines.flow.Flow

@Dao
interface MarvelDao {
    @Insert
    suspend fun insertCharacter(hero: Hero)

    @Query("SELECT * FROM MarvelTable")
    fun getAllHeroes(): Flow<List<Hero>>

    @Query("SELECT * FROM MarvelTable WHERE id =(:heroId)")
    fun getHeroById(heroId: Int): Flow<Hero>

    @Query("SELECT (SELECT COUNT(*) FROM MarvelTable) == 0")
    fun isEmpty(): Flow<Boolean>

    @Query("DELETE FROM MarvelTable")
    suspend fun deleteAll()

}