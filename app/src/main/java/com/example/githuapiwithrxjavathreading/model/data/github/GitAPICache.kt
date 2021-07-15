package com.example.githuapiwithrxjavathreading.model.data.github

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "gitAPI_cache")
data class GitAPICache(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name="cache_id")
    val cacheID: Int,

    @ColumnInfo(name = "data")
    val data : String
)
