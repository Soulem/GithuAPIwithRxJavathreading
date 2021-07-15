package com.example.githuapiwithrxjavathreading.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.multidex.MultiDex
import com.example.githuapiwithrxjavathreading.R
import com.example.githuapiwithrxjavathreading.databinding.ActivityMainBinding
import com.example.githuapiwithrxjavathreading.model.data.github.GitRetrofitItem
import com.example.githuapiwithrxjavathreading.utl.GitAPISelector
import com.example.githuapiwithrxjavathreading.view.fragment.RecyclerDisplayFragment
import com.example.githuapiwithrxjavathreading.view.fragment.SearchLayoutFragment
import com.example.githuapiwithrxjavathreading.viewmodel.ObjectViewModel

class MainActivity : AppCompatActivity(), GitAPISelector {
    private lateinit var binding: ActivityMainBinding
    private lateinit var recyclerDisplayFragment : RecyclerDisplayFragment
    private lateinit var searchLayoutFragment : SearchLayoutFragment
    //private lateinit var hFA: HomeFragmentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //hFA = HomeFragmentAdapter(supportFragmetnManager)
        // binding.mainVp.adapter = hFA

        MultiDex.install(this)
        recyclerDisplayFragment = supportFragmentManager.findFragmentById(R.id.display_frameLayout) as RecyclerDisplayFragment
        searchLayoutFragment = supportFragmentManager.findFragmentById(R.id.search_frameLayout) as SearchLayoutFragment
    }

    override fun openDetailsFragment(item: GitRetrofitItem) {
        TODO("Not yet implemented")
    }
}