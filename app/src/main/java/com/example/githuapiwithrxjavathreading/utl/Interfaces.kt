package com.example.githuapiwithrxjavathreading.utl

import com.example.githuapiwithrxjavathreading.model.data.github.repo.GitRetrofitUserRepo

interface GitAPISelector{
    fun openDetailsFragment(item: GitRetrofitUserRepo)
}