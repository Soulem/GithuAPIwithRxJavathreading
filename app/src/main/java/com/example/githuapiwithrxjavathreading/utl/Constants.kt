package com.example.githuapiwithrxjavathreading.utl

class Constants {
    companion object{
        //API
        const val BASE_URL = "https://api.github.com/"
        const val USER_NAME = "user_name"
        const val REPO_NAME = "repo_name"
        const val USER_REPOS_END_POINT = "users/{$USER_NAME}/repos"
        const val USER_END_POINT = "users/{$USER_NAME}"
        const val USERS_END_POINT = "users"
        const val USER_COMMITS_END_POINT = "repos/{$USER_NAME}/{$REPO_NAME}/commits"

        //Database
        const val DATABASE_NAME = "GitUsersDatabaseV6.db"
        const val DEFAULT_USERS = "Soulem"
    }
}