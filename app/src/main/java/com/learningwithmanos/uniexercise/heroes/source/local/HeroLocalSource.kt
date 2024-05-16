package com.learningwithmanos.uniexercise.heroes.source.local

import android.util.Log
import com.learningwithmanos.uniexercise.AppPreferences
import com.learningwithmanos.uniexercise.heroes.data.Hero
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Interface that wraps the local storage data framework that is used
 */
interface HeroLocalSource {

    /**
     * @return true if heroes are stored locally else false
     */

    /**
     * Stores a list of heroes to the local data storage
     * @param heroes list of heroes to be stored
     */
    suspend fun storeHero(hero: Hero)
    /**
     * @return the list of heroes stored at the local storage
     */
    fun getHeroes(): Flow<List<Hero>>


    suspend fun updateApi(apikey: String, privatekey: String)

}

class HeroLocalSourceImpl @Inject constructor(private val marvelDao : MarvelDao): HeroLocalSource {

    override suspend fun storeHero(hero: Hero) {
        marvelDao.insertCharacter(hero)
    }


    override fun getHeroes(): Flow<List<Hero>> {
        return marvelDao.getAllHeroes()
    }


    override suspend fun updateApi(apikey: String, privatekey: String) {
        if (!(AppPreferences.apikey.equals(apikey)) || !(AppPreferences.privatekey.equals(privatekey))) {
            AppPreferences.apikey = apikey
            AppPreferences.privatekey = privatekey
            marvelDao.deleteAll()
            Log.d("Dispacher RUN", "setApi: local db deleted API: $apikey Private: $privatekey")
        }
    }

}