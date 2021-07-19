package com.example.githuapiwithrxjavathreading.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.githuapiwithrxjavathreading.databinding.FragmentSearchLayoutBinding
import com.example.githuapiwithrxjavathreading.view.adapter.RepoRecyclerDisplayAdapter
import com.example.githuapiwithrxjavathreading.viewmodel.ObjectViewModel

class SearchLayoutFragment() : Fragment() {
    private lateinit var binding: FragmentSearchLayoutBinding
    private lateinit var adapterRepo: RepoRecyclerDisplayAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchLayoutBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.searchUserButton.setOnClickListener{
            ObjectViewModel.instance.searchUser(binding.userNameEditText.text.toString())
            ObjectViewModel.instance.gitRepoData.observe(viewLifecycleOwner, {
                adapterRepo.apiList = it
            })
        }
    }

    fun setAdapter(adapterRepo: RepoRecyclerDisplayAdapter){
        this.adapterRepo = adapterRepo
    }
}