package com.example.githuapiwithrxjavathreading.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githuapiwithrxjavathreading.model.data.github.GitRetrofitItem
import com.example.githuapiwithrxjavathreading.network.GitAPIRetrofit
import com.example.githuapiwithrxjavathreading.utl.GitAPISingleton.Companion.gitAPIComponent
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class ObjectViewModel: ViewModel() {
    companion object {
        val instance  = ObjectViewModel()
    }

    val gitAPIData = MutableLiveData<List<GitRetrofitItem>>()
    private val compDisposable = CompositeDisposable()
    private val gitAPIRetrofit = GitAPIRetrofit()

    init {
        compDisposable.add(
            gitAPIComponent.getComponentRepository().readFromRemoteSource("Soulem")
                .observeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .map {

                    gitAPIComponent.getComponentRepository().saveToCache(it)
                    Log.d("TAG_X", "saving to cache - on ${Thread.currentThread().name}")
                    it
                }
                .subscribe({results ->
                    Log.d("TAG_X", "update LiveData on ${Thread.currentThread().name}")
                    gitAPIData.postValue(results)

                },  {throwable ->
                    Log.d("TAG_X", "Oops: ${throwable.localizedMessage}")
                    val list = gitAPIComponent.getComponentRepository().readFromCache()
                    gitAPIData.postValue(list)
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
            gitAPIComponent.getComponentRepository().readFromRemoteSource(userName)
                .observeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .map {

                    gitAPIComponent.getComponentRepository().saveToCache(it)
                    Log.d("TAG_X", "saving to cache - on ${Thread.currentThread().name}")
                    it
                }
                .subscribe({results ->
                    Log.d("TAG_X", "update LiveData on ${Thread.currentThread().name}")
                    gitAPIData.postValue(results)

                },  {throwable ->
                    Log.d("TAG_X", "Oops: ${throwable.localizedMessage}")
                    val list = gitAPIComponent.getComponentRepository().readFromCache()
                    gitAPIData.postValue(list)
                })
            //high order function - function that can take
            //other functions as arguments or have a function as a return type
        )
    }
}