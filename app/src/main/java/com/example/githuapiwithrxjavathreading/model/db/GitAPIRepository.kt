package com.example.githuapiwithrxjavathreading.model.db

import com.example.githuapiwithrxjavathreading.model.data.github.repo.GitAPIRepoCache
import com.example.githuapiwithrxjavathreading.model.data.github.repo.GitRetrofitUserRepo
import com.example.githuapiwithrxjavathreading.model.db.GitAPIDatabase.Companion.getDao
import com.example.githuapiwithrxjavathreading.network.GitAPIRetrofit
import com.google.gson.Gson
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GitAPIRepository  @Inject constructor(private val gitAPIRetrofit : GitAPIRetrofit){
    fun readFromRemoteSource(username : String) : Single<List<GitRetrofitUserRepo>> = gitAPIRetrofit.getRepositoriesRemote(username)

    fun readFromCache(userName : String): List<GitRetrofitUserRepo> {
        val cache = getDao().readFromCache(userName)
        return listOf(Gson().fromJson(cache.data, GitRetrofitUserRepo::class.java))
    }

    fun saveToCache(response: List<GitRetrofitUserRepo>){
        val json = Gson().toJson(response)
        // userName = response[0].owner.login
        getDao().cacheData((GitAPIRepoCache(response[0].owner.login, json)))
    }
}