package com.example.githuapiwithrxjavathreading.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.multidex.MultiDex
import com.example.githuapiwithrxjavathreading.R
import com.example.githuapiwithrxjavathreading.databinding.ActivityMainBinding
import com.example.githuapiwithrxjavathreading.model.data.github.GitRetrofitItem
import com.example.githuapiwithrxjavathreading.utl.GitAPISelector
import com.example.githuapiwithrxjavathreading.view.fragment.DisplayItemFragment
import com.example.githuapiwithrxjavathreading.view.fragment.RecyclerDisplayFragment
import com.example.githuapiwithrxjavathreading.view.fragment.SearchLayoutFragment

class MainActivity : AppCompatActivity(), GitAPISelector {
    private lateinit var binding: ActivityMainBinding
    private lateinit var recyclerDisplayFragment : RecyclerDisplayFragment
    private lateinit var searchLayoutFragment : SearchLayoutFragment


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //hFA = HomeFragmentAdapter(supportFragmetnManager)
        // binding.mainVp.adapter = hFA

        MultiDex.install(this)
        recyclerDisplayFragment = supportFragmentManager.findFragmentById(R.id.display_frameLayout) as RecyclerDisplayFragment
        searchLayoutFragment = supportFragmentManager.findFragmentById(R.id.search_frameLayout) as SearchLayoutFragment
        searchLayoutFragment.setAdapter(recyclerDisplayFragment.getAdapter())
    }

    override fun openDetailsFragment(item: GitRetrofitItem) {
        val fragment = DisplayItemFragment.getInstance(item)

        Log.d("TAG_X", "odf")
        supportFragmentManager.beginTransaction()
            .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right, android.R.anim.slide_in_left, android.R.anim.slide_out_right)
            .add(R.id.detail_item_frame, fragment)
            .addToBackStack(fragment.tag)
            .commit()
    }
}