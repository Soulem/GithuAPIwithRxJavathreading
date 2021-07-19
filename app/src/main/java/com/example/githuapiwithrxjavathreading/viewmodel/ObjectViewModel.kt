package com.example.githuapiwithrxjavathreading.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githuapiwithrxjavathreading.model.data.github.commit.GitRetrofitUserCommitItem
import com.example.githuapiwithrxjavathreading.model.data.github.repo.GitRetrofitUserRepoItem
import com.example.githuapiwithrxjavathreading.model.data.github.user.GitRetrofitUser
import com.example.githuapiwithrxjavathreading.network.GitAPIRetrofit
import com.example.githuapiwithrxjavathreading.utl.Constants.Companion.DEFAULT_USERS
import com.example.githuapiwithrxjavathreading.utl.GitAPISingleton.Companion.gitAPIComponent
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class ObjectViewModel: ViewModel() {
    companion object {
        val instance  = ObjectViewModel()
    }

    val gitRepoData = MutableLiveData<List<GitRetrofitUserRepoItem>>()
    val gitUserData = MutableLiveData<List<GitRetrofitUser>>()
    val gitCommitData = MutableLiveData<List<GitRetrofitUserCommitItem>>()
    private val compDisposable = CompositeDisposable()

    init {
        compDisposable.add(
            gitAPIComponent.getComponentRepository().readRepoFromRemoteSource("Soulem")
                .observeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .map {

                    gitAPIComponent.getComponentRepository().saveReposToCache(it)
                    Log.d("TAG_X", "saving to cache - on ${Thread.currentThread().name}")
                    it
                }
                .subscribe({results ->
                    Log.d("TAG_X", "update LiveData on ${Thread.currentThread().name}")
                    gitRepoData.postValue(results)

                },  {throwable ->
                    Log.d("TAG_X", "Oops: ${throwable.localizedMessage}")
                    val list = gitAPIComponent.getComponentRepository().readReposFromCache(DEFAULT_USERS)
                    gitRepoData.postValue(list)
                })
            //high order function - function that can take
            //other functions as arguments or have a function as a return type
        )

        compDisposable.add(
            gitAPIComponent.getComponentRepository().readUserFromRemoteSource("Soulem")
                .observeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .map {

                    gitAPIComponent.getComponentRepository().saveUserToCache(it)
                    Log.d("TAG_X", "saving to cache - on ${Thread.currentThread().name}")
                    it
                }
                .subscribe({results ->
                    Log.d("TAG_X", "update LiveData on ${Thread.currentThread().name}")
                    val list :List<GitRetrofitUser> = listOf(results)
                    gitUserData.postValue(list)

                },  {throwable ->
                    Log.d("TAG_X", "Oops: ${throwable.localizedMessage}")
                    val list = gitAPIComponent.getComponentRepository().readUserFromCache()
                    gitUserData.postValue(list)
                })
            //high order function - function that can take
            //other functions as arguments or have a function as a return type
        )

        compDisposable.add(
            gitAPIComponent.getComponentRepository().readCommitsFromRemoteSource("Soulem", "AgeUPApp")
                .observeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .map {

                    gitAPIComponent.getComponentRepository().saveCommitsToCache(it)
                    Log.d("TAG_X", "saving to cache - on ${Thread.currentThread().name}")
                    it
                }
                .subscribe({results ->
                    Log.d("TAG_X", "update LiveData on ${Thread.currentThread().name}")
                    gitCommitData.postValue(results)

                },  {throwable ->
                    Log.d("TAG_X", "Oops: ${throwable.localizedMessage}")
                    val list = gitAPIComponent.getComponentRepository().readCommitsFromCache(DEFAULT_USERS)
                    gitCommitData.postValue(list)
                })
            //high order function - function that can take
            //other functions as arguments or have a function as a return type
        )
    }

    override fun onCleared() {
        super.onCleared()
        compDisposable.clear()
    }

    fun searchUser(userName : String){
        if (userName == "")
            return
        compDisposable.add(
            gitAPIComponent.getComponentRepository().readUserFromRemoteSource(userName)
                .observeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .map {

                    gitAPIComponent.getComponentRepository().saveUserToCache(it)
                    Log.d("TAG_X", "saving to cache - on ${Thread.currentThread().name}")
                    it
                }
                .subscribe({results ->
                    Log.d("TAG_X", "update LiveData on ${Thread.currentThread().name}")
                    val list :List<GitRetrofitUser> = listOf(results)
                    gitUserData.postValue(list)

                },  {throwable ->
                    Log.d("TAG_X", "Oops: ${throwable.localizedMessage}")
                    val list = gitAPIComponent.getComponentRepository().readUserFromCache()
                    gitUserData.postValue(list)
                })
            //high order function - function that can take
            //other functions as arguments or have a function as a return type
        )
    }

    fun searchRepos(userName : String){
        compDisposable.add(
            gitAPIComponent.getComponentRepository().readRepoFromRemoteSource(userName)
                .observeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .map {

                    gitAPIComponent.getComponentRepository().saveReposToCache(it)
                    Log.d("TAG_X", "saving to cache - on ${Thread.currentThread().name}")
                    it
                }
                .subscribe({results ->
                    Log.d("TAG_X", "update LiveData on ${Thread.currentThread().name}")
                    gitRepoData.postValue(results)

                },  {throwable ->
                    Log.d("TAG_X", "Oops: ${throwable.localizedMessage}")
                    val list = gitAPIComponent.getComponentRepository().readReposFromCache(DEFAULT_USERS)
                    gitRepoData.postValue(list)
                })
            //high order function - function that can take
            //other functions as arguments or have a function as a return type
        )
    }

    fun searchCommits(userName : String, repoName : String){
        compDisposable.add(
            gitAPIComponent.getComponentRepository().readCommitsFromRemoteSource(userName, repoName)
                .observeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .map {

                    gitAPIComponent.getComponentRepository().saveCommitsToCache(it)
                    Log.d("TAG_X", "saving to cache - on ${Thread.currentThread().name}")
                    it
                }
                .subscribe({results ->
                    Log.d("TAG_X", "update LiveData on ${Thread.currentThread().name}")
                    gitCommitData.postValue(results)

                },  {throwable ->
                    Log.d("TAG_X", "Oops: ${throwable.localizedMessage}")
                    val list = gitAPIComponent.getComponentRepository().readCommitsFromCache(DEFAULT_USERS)
                    gitCommitData.postValue(list)
                })
            //high order function - function that can take
            //other functions as arguments or have a function as a return type
        )
    }
}