package com.example.githuapiwithrxjavathreading.model.data.github.commit

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "gitCommit_cache")
data class GitAPICommitCache(
    @PrimaryKey()
    @ColumnInfo(name="name")
    val uniqueName: String,

    @ColumnInfo(name = "data")
    val data : String
)
