package com.example.githuapiwithrxjavathreading.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.multidex.MultiDex
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.githuapiwithrxjavathreading.R
import com.example.githuapiwithrxjavathreading.databinding.ActivityMainBinding
import com.example.githuapiwithrxjavathreading.model.data.github.commit.GitRetrofitUserCommitItem
import com.example.githuapiwithrxjavathreading.model.data.github.repo.GitRetrofitUserRepoItem
import com.example.githuapiwithrxjavathreading.model.data.github.user.GitRetrofitUser
import com.example.githuapiwithrxjavathreading.utl.GitAPISelector
import com.example.githuapiwithrxjavathreading.view.fragment.*
import com.example.githuapiwithrxjavathreading.workmanager.UploadWorker
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity(), GitAPISelector {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        MultiDex.install(this)

         //This is where i create the SearchLayoutFragment to display
        val searchFragment = SearchLayoutFragment.getInstance(this)

        supportFragmentManager.beginTransaction()
            .add(R.id.detail_item_frame, searchFragment)
            .addToBackStack(searchFragment.tag)
            .commit()


        val uploadWorkRequest =
            PeriodicWorkRequestBuilder<UploadWorker>(24, TimeUnit.HOURS)
                .build()

        // Queue it up in the work manager.
        // The WorkManager is a singleton that needs a context.
        WorkManager
            .getInstance(this)
            .enqueueUniquePeriodicWork("My Work", ExistingPeriodicWorkPolicy.KEEP, uploadWorkRequest)
    }

    override fun openUserDetailsFragment(item: GitRetrofitUser) {
        val fragment = UserDisplayFragment.getInstance(this, item)

        Log.d("TAG_X", "odf")
        supportFragmentManager.beginTransaction()
            .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right, android.R.anim.slide_in_left, android.R.anim.slide_out_right)
            .replace(R.id.detail_item_frame, fragment)
            .addToBackStack(fragment.tag)
            .commit()
    }

    override fun openRepoDetailsFragment(gitRetrofitItem: GitRetrofitUserRepoItem) {
        val fragment = RepoDisplayFragment.getInstance(this, gitRetrofitItem)

        supportFragmentManager.beginTransaction()
            .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right, android.R.anim.slide_in_left, android.R.anim.slide_out_right)
            .replace(R.id.detail_item_frame, fragment)
            .addToBackStack(fragment.tag)
            .commit()
    }

    override fun openCommitDetailsFragment(gitRetrofitItem: GitRetrofitUserCommitItem) {
        val fragment = CommitDisplayFragment.getInstance(gitRetrofitItem, this)

        supportFragmentManager.beginTransaction()
            .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right, android.R.anim.slide_in_left, android.R.anim.slide_out_right)
            .replace(R.id.detail_item_frame, fragment)
            .addToBackStack(fragment.tag)
            .commit()
    }
}