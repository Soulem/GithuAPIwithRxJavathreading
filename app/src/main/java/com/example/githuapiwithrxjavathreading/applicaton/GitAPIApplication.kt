package com.example.githuapiwithrxjavathreading.applicaton

import androidx.multidex.MultiDexApplication
import com.example.githuapiwithrxjavathreading.di.component.DaggerGitAPIComponent
import com.example.githuapiwithrxjavathreading.model.db.GitAPIDatabase
import com.example.githuapiwithrxjavathreading.utl.GitAPISingleton

class GitAPIApplication: MultiDexApplication() {
    override fun onCreate(){
        super.onCreate()
        GitAPIDatabase.initializeDatabase(this)
        GitAPISingleton.gitAPIComponent = DaggerGitAPIComponent.create()
    }
}