package com.example.githuapiwithrxjavathreading.utl

import com.example.githuapiwithrxjavathreading.model.data.github.commit.GitRetrofitUserCommitItem
import com.example.githuapiwithrxjavathreading.model.data.github.repo.GitRetrofitUserRepoItem
import com.example.githuapiwithrxjavathreading.model.data.github.user.GitRetrofitUser

interface GitAPISelector{
    fun openUserDetailsFragment(gitRetrofitItem: GitRetrofitUser)
    fun openRepoDetailsFragment(gitRetrofitItem: GitRetrofitUserRepoItem)
    fun openCommitDetailsFragment(gitRetrofitItem: GitRetrofitUserCommitItem)
}