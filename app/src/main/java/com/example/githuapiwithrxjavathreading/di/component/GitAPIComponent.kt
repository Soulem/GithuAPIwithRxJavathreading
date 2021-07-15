package com.example.githuapiwithrxjavathreading.di.component

import com.example.githuapiwithrxjavathreading.model.db.GitAPIRepository
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component
interface GitAPIComponent {
    fun getComponentRepository(): GitAPIRepository
}