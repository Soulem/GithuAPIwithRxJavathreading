package com.example.githuapiwithrxjavathreading.utl

class Constants {
    companion object{
        //API
        const val BASE_URL = "https://api.github.com/"
        const val USER_NAME = "user_name"
        const val END_POINT = "users/{$USER_NAME}/repos"

        //Database
        const val DATABASE_NAME = "GetRepositoryByUser.db"
        const val CACHE_KEY = 1
    }
}