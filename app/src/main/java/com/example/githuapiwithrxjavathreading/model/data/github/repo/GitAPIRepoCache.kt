package com.example.githuapiwithrxjavathreading.model.data.github.repo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "gitAPI_cache")
data class GitAPIRepoCache(
    @PrimaryKey()
    @ColumnInfo(name="name")
    val uniqueName: String,

    @ColumnInfo(name = "data")
    val data : String
)
