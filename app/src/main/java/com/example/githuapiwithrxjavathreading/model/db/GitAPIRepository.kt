package com.example.githuapiwithrxjavathreading.model.db

import com.example.githuapiwithrxjavathreading.model.data.github.GitAPICache
import com.example.githuapiwithrxjavathreading.model.data.github.GitRetrofitItem
import com.example.githuapiwithrxjavathreading.model.db.GitAPIDatabase.Companion.getDao
import com.example.githuapiwithrxjavathreading.network.GitAPIRetrofit
import com.example.githuapiwithrxjavathreading.utl.Constants.Companion.CACHE_KEY
import com.google.gson.Gson
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GitAPIRepository  @Inject constructor(private val gitAPIRetrofit : GitAPIRetrofit){
    fun readFromRemoteSource(username : String) : Single<List<GitRetrofitItem>> = gitAPIRetrofit.getRepositoriesRemote(username)

    fun readFromCache(): List<GitRetrofitItem> {
        val cache = getDao().readFromCache()
        return listOf(Gson().fromJson(cache.data, GitRetrofitItem::class.java))
    }

    fun saveToCache(response: List<GitRetrofitItem>){
        val json = Gson().toJson(response)
        getDao().cacheData((GitAPICache(CACHE_KEY, json)))
    }
}