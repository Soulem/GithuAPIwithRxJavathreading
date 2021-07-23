package com.example.githuapiwithrxjavathreading.model.db

import com.example.githuapiwithrxjavathreading.model.data.github.commit.GitAPICommitCache
import com.example.githuapiwithrxjavathreading.model.data.github.commit.GitRetrofitUserCommitItem
import com.example.githuapiwithrxjavathreading.model.data.github.repo.GitAPIRepoCache
import com.example.githuapiwithrxjavathreading.model.data.github.repo.GitRetrofitUserRepoItem
import com.example.githuapiwithrxjavathreading.model.data.github.user.GitAPIUserCache
import com.example.githuapiwithrxjavathreading.model.data.github.user.GitRetrofitUser
import com.example.githuapiwithrxjavathreading.model.db.GitAPIDatabase.Companion.getDao
import com.example.githuapiwithrxjavathreading.network.GitAPIRetrofit
import com.google.gson.Gson
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GitAPIRepository  @Inject constructor(private val gitAPIRetrofit : GitAPIRetrofit){
    fun readRepoFromRemoteSource(username : String) : Single<List<GitRetrofitUserRepoItem>> = gitAPIRetrofit.getRepositoriesRemote(username)

    fun readUserFromRemoteSource(username :String): Single<GitRetrofitUser> = gitAPIRetrofit.getUserRemote(username)

    fun readUsersFromRemoteSource(): Single<List<GitRetrofitUser>> = gitAPIRetrofit.getUsersRemote()

    fun readCommitsFromRemoteSource(username : String, reponame : String) : Single<List<GitRetrofitUserCommitItem>> = gitAPIRetrofit.getCommitsRemote(username, reponame)

    fun readReposFromCache(userName : String): List<GitRetrofitUserRepoItem> {
        val cache = getDao().readRepoFromCache(userName)
        var list = mutableListOf<GitRetrofitUserRepoItem>()
        if (cache != null)
            list.addAll(Gson().fromJson(cache.data, Array<GitRetrofitUserRepoItem>::class.java))
        return list
    }

    fun saveReposToCache(response: List<GitRetrofitUserRepoItem>){
        val json = Gson().toJson(response)
        // userName = response[0].owner.login
        getDao().cacheRepoData((GitAPIRepoCache(response[0].owner.login, json)))
    }

    fun readUserFromCache(): List<GitRetrofitUser> {
        val cache = getDao().readUserFromCache()

        var gsonList = mutableListOf<GitRetrofitUser>()

        for (user : GitAPIUserCache in cache){
            gsonList.add(Gson().fromJson(user.data, GitRetrofitUser::class.java))
        }


        return gsonList
    }

    fun readUserFromCache(userName : String): GitRetrofitUser {
        val cache = getDao().readUserFromCache(userName)
        return Gson().fromJson(cache.data, GitRetrofitUser::class.java)
    }

    fun saveUserToCache(response: GitRetrofitUser){
        val json = Gson().toJson(response)
        // userName = response[0].owner.login
        getDao().cacheUserData((GitAPIUserCache(response.login, json)))
    }

    fun saveUserToCache(response: List<GitRetrofitUser>){
        val json = Gson().toJson(response)

        getDao().cacheUserData((GitAPIUserCache(response[0].login, json)))
    }

    fun readCommitsFromCache(userName : String): List<GitRetrofitUserCommitItem> {
        val cache = getDao().readCommitFromCache(userName)

        var list = mutableListOf<GitRetrofitUserCommitItem>()
        list.addAll(Gson().fromJson(cache.data, Array<GitRetrofitUserCommitItem>::class.java))

        return list
    }

    fun saveCommitsToCache(response: List<GitRetrofitUserCommitItem>){
        val json = Gson().toJson(response)

        getDao().cacheCommitData((GitAPICommitCache(response[0].author.login, json)))
    }
}