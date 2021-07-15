package com.example.githuapiwithrxjavathreading.utl

import com.example.githuapiwithrxjavathreading.di.component.GitAPIComponent

class GitAPISingleton {
    companion object{
        lateinit var gitAPIComponent : GitAPIComponent
    }
}