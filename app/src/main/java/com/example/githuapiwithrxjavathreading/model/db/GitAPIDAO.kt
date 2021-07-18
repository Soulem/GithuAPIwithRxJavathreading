package com.example.githuapiwithrxjavathreading.model.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.example.githuapiwithrxjavathreading.model.data.github.repo.GitAPIRepoCache

@Dao
interface GitAPIDAO {
    @Insert(onConflict = REPLACE)
    fun cacheData(data: GitAPIRepoCache)

    @Query("SELECT * FROM gitAPI_cache WHERE name = :username")
    fun readFromCache(username: String): GitAPIRepoCache
}