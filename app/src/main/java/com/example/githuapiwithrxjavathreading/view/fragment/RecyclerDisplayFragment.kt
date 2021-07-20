package com.example.githuapiwithrxjavathreading.view.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.githuapiwithrxjavathreading.databinding.FragmentRecyclerDisplayLayoutBinding
import com.example.githuapiwithrxjavathreading.model.data.github.repo.GitRetrofitUserRepoItem
import com.example.githuapiwithrxjavathreading.utl.GitAPISelector
import com.example.githuapiwithrxjavathreading.view.activity.MainActivity
import com.example.githuapiwithrxjavathreading.view.adapter.RepoRecyclerDisplayAdapter
import com.example.githuapiwithrxjavathreading.viewmodel.ObjectViewModel

class RecyclerDisplayFragment : Fragment(), RepoRecyclerDisplayAdapter.GitAPIRepoDelegate {
    private lateinit var binding: FragmentRecyclerDisplayLayoutBinding
    private lateinit var gitAPISelector: GitAPISelector
    private val adapter = RepoRecyclerDisplayAdapter(this)

    override fun onAttach(context: Context){
        super.onAttach(context)
        gitAPISelector = context as MainActivity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRecyclerDisplayLayoutBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerViewDisplay.adapter = adapter
        ObjectViewModel.instance.gitRepoData.observe(viewLifecycleOwner, {
            adapter.apiList = it
        })
    }

    override fun selectItem(gitRetrofitItemItem: GitRetrofitUserRepoItem) {
        //gitAPISelector.openRepoDetailsFragment(gitRetrofitItemItem)
    }

    fun getAdapter(): RepoRecyclerDisplayAdapter = this.adapter
}