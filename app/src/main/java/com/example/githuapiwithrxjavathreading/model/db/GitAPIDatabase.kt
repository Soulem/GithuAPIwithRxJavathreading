package com.example.githuapiwithrxjavathreading.model.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.githuapiwithrxjavathreading.model.data.github.GitAPICache
import com.example.githuapiwithrxjavathreading.utl.Constants.Companion.DATABASE_NAME

@Database(version = 1, entities = [GitAPICache::class])
abstract class GitAPIDatabase: RoomDatabase() {
    abstract fun getDAO(): GitAPIDAO
    companion object{
        private lateinit var gitAPIDatabase: GitAPIDatabase

        fun initializeDatabase(context: Context){
            gitAPIDatabase = Room.databaseBuilder(
                context,
                GitAPIDatabase::class.java,
                DATABASE_NAME
            ).build()
        }
        fun getDao() = gitAPIDatabase.getDAO()
    }
}