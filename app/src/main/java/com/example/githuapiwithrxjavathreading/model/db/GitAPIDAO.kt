package com.example.githuapiwithrxjavathreading.model.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.example.githuapiwithrxjavathreading.model.data.github.commit.GitAPICommitCache
import com.example.githuapiwithrxjavathreading.model.data.github.repo.GitAPIRepoCache
import com.example.githuapiwithrxjavathreading.model.data.github.user.GitAPIUserCache

@Dao
interface GitAPIDAO {
    @Insert(onConflict = REPLACE)
    fun cacheRepoData(data: GitAPIRepoCache)

    @Insert(onConflict = REPLACE)
    fun cacheUserData(data: GitAPIUserCache)

    @Insert(onConflict = REPLACE)
    fun cacheCommitData(data: GitAPICommitCache)

    @Query("SELECT * FROM gitRepo_cache WHERE name = :username LIMIT 1")
    fun readRepoFromCache(username: String): GitAPIRepoCache

    @Query("SELECT * FROM gitUser_cache")
    fun readUserFromCache(): List<GitAPIUserCache>

    @Query("SELECT * FROM gitUser_cache WHERE name = :username")
    fun readUserFromCache(username : String) : GitAPIUserCache

    @Query("SELECT * FROM gitCommit_cache WHERE name = :username")
    fun readCommitFromCache(username: String): GitAPICommitCache
}