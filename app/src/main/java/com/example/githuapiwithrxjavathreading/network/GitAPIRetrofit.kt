package com.example.githuapiwithrxjavathreading.network

import com.example.githuapiwithrxjavathreading.model.data.github.commit.GitRetrofitUserCommitItem
import com.example.githuapiwithrxjavathreading.model.data.github.repo.GitRetrofitUserRepoItem
import com.example.githuapiwithrxjavathreading.model.data.github.user.GitRetrofitUser
import com.example.githuapiwithrxjavathreading.utl.Constants.Companion.BASE_URL
import com.example.githuapiwithrxjavathreading.utl.Constants.Companion.REPO_NAME
import com.example.githuapiwithrxjavathreading.utl.Constants.Companion.USER_COMMITS_END_POINT
import com.example.githuapiwithrxjavathreading.utl.Constants.Companion.USER_END_POINT
import com.example.githuapiwithrxjavathreading.utl.Constants.Companion.USER_REPOS_END_POINT
import com.example.githuapiwithrxjavathreading.utl.Constants.Companion.USER_NAME
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GitAPIRetrofit @Inject constructor()  {
    private lateinit var gitAPIService:  GitAPIService

    init {
        gitAPIService = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(GitAPIService::class.java)
    }

    fun getRepositoriesRemote(username : String) = gitAPIService.getUserRepositories(username)

    fun getUsersRemote(username : String) = gitAPIService.getUserInfo(username)

    fun getCommitsRemote(username : String, reponame : String) = gitAPIService.getUserCommits(username, reponame)

    interface GitAPIService{
        @GET(USER_REPOS_END_POINT)
        fun getUserRepositories(@Path(USER_NAME) username : String) : Single<List<GitRetrofitUserRepoItem>>

        @GET(USER_END_POINT)
        fun getUserInfo(@Path(USER_NAME) username :String): Single<GitRetrofitUser>

        @GET(USER_COMMITS_END_POINT)
        fun getUserCommits(@Path(USER_NAME) username :String, @Path(REPO_NAME) reponame :String): Single<List<GitRetrofitUserCommitItem>>


    }
}