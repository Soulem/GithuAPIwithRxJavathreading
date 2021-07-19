package com.example.githuapiwithrxjavathreading.model.data.github.user

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "gitUser_cache")
data class GitAPIUserCache(
    @PrimaryKey()
    @ColumnInfo(name="name")
    val uniqueName: String,

    @ColumnInfo(name = "data")
    val data : String
)
