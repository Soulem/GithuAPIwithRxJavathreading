package com.example.githuapiwithrxjavathreading.model.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.example.githuapiwithrxjavathreading.model.data.github.GitAPICache
import com.example.githuapiwithrxjavathreading.utl.Constants.Companion.CACHE_KEY

@Dao
interface GitAPIDAO {
    @Insert(onConflict = REPLACE)
    fun cacheData(data: GitAPICache)

    @Query("SELECT * FROM gitAPI_cache WHERE cache_id = $CACHE_KEY")
    fun readFromCache(): GitAPICache
}