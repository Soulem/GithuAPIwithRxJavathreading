package com.example.githuapiwithrxjavathreading.utl

import com.example.githuapiwithrxjavathreading.model.data.github.GitRetrofitItem

interface GitAPISelector{
    fun openDetailsFragment(item: GitRetrofitItem)
}