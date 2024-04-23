package com.learningwithmanos.uniexercise.heroes.repo

import android.util.Log
import com.learningwithmanos.uniexercise.heroes.data.Hero
import com.learningwithmanos.uniexercise.heroes.source.local.HeroLocalSource
import com.learningwithmanos.uniexercise.heroes.source.remote.HeroRemoteSource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

/**
 * A repository interface that is used to coordinate the usage of the LocalSource and the
 * RemoteSource.
 */
interface HeroRepository {

    /**
     * In the scope of this method it is decided from which source the data should be retrieved from.
     * Retrieve from local if data are stored, otherwise retrieve data from remote and also store
     * the data to the local.
     *
     * @return list of heroes
     */
    suspend fun getHeroes(): Flow<List<Hero>>

    suspend fun getQuery(): Flow<List<Hero>>
    suspend fun update(id: Int, description: String?)
    suspend fun setApiValues(apikey: String, privatekey: String)
    suspend fun setDesc(id: Int)
}

class HeroRepositoryImpl @Inject constructor(
    private val heroRemoteSource: HeroRemoteSource,
    private val heroLocalSource: HeroLocalSource,
) : HeroRepository {
    @OptIn(ExperimentalCoroutinesApi::class)
    override suspend fun getHeroes(): Flow<List<Hero>>  {
        Log.d("MyLog","Repo Exec")
        return heroLocalSource.isEmpty().flatMapLatest { isEmpty ->
            if (isEmpty) {
                Log.d("MyLog","Repo Was empty")
                val heroList = heroRemoteSource.getHeroes()
                heroLocalSource.storeHeroes(heroList)
                flowOf((heroList))
            } else {
                Log.d("MyLog","Repo Was not")
                heroLocalSource.getHeroes()
            }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override suspend fun getQuery(): Flow<List<Hero>> {
        Log.d("MyLog","getQuery exec")
        return heroLocalSource.getQuery()
    }

    override suspend fun update(id: Int, description: String?) {
        heroLocalSource.update(id, description)
    }

    override suspend fun setApiValues(apikey: String, privatekey: String) {
        heroLocalSource.setApiValues(apikey, privatekey)
    }

    override suspend fun setDesc(id: Int) {
        val hero: Hero = heroRemoteSource.getDesc(id)
        heroLocalSource.update(hero.id, hero.description)
    }

}