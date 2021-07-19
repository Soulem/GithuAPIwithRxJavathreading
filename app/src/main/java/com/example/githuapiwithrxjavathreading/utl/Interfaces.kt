package com.example.githuapiwithrxjavathreading.utl

import com.example.githuapiwithrxjavathreading.model.data.github.repo.GitRetrofitUserRepoItem

interface GitAPISelector{
    fun openDetailsFragment(item: GitRetrofitUserRepoItem)
}