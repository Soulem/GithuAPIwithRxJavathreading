package com.example.githuapiwithrxjavathreading.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.multidex.MultiDex
import com.example.githuapiwithrxjavathreading.R
import com.example.githuapiwithrxjavathreading.databinding.ActivityMainBinding
import com.example.githuapiwithrxjavathreading.model.data.github.user.GitRetrofitUser
import com.example.githuapiwithrxjavathreading.utl.GitAPISelector
import com.example.githuapiwithrxjavathreading.view.fragment.*
import com.example.githuapiwithrxjavathreading.viewmodel.ObjectViewModel

class MainActivity : AppCompatActivity(), GitAPISelector {
    private lateinit var binding: ActivityMainBinding
    private lateinit var recyclerDisplayFragment : RecyclerDisplayFragment
    private lateinit var searchLayoutFragment : SearchLayoutFragment


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        MultiDex.install(this)
         //This is where i create the SearchLayoutFragment to display
        val searchFragment = SearchLayoutFragment.getInstance()
        searchFragment.setSelector(this)
        supportFragmentManager.beginTransaction()
            .add(R.id.detail_item_frame, searchFragment)
            .addToBackStack(searchFragment.tag)
            .commit()
    }

    override fun openRepoDetailsFragment(item: GitRetrofitUser) {

        ObjectViewModel.instance.searchUser(item.login)
        ObjectViewModel.instance.gitUsersData.observe(this, {
            val fragment = UserDisplayFragment.getInstance(it[0])
            Log.d("TAG_X", "odf")
            supportFragmentManager.beginTransaction()
                .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right, android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                .replace(R.id.detail_item_frame, fragment)
                .addToBackStack(fragment.tag)
                .commit()
        })
    }
}