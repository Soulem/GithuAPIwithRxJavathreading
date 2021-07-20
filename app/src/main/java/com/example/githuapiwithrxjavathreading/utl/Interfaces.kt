package com.example.githuapiwithrxjavathreading.utl

import com.example.githuapiwithrxjavathreading.model.data.github.user.GitRetrofitUser

interface GitAPISelector{
    fun openRepoDetailsFragment(gitRetrofitItem: GitRetrofitUser)
}