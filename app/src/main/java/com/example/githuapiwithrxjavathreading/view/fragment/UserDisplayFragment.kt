package com.example.githuapiwithrxjavathreading.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.githuapiwithrxjavathreading.databinding.FragmentCommitDisplayBinding
import com.example.githuapiwithrxjavathreading.databinding.FragmentUserDisplayBinding
import com.example.githuapiwithrxjavathreading.model.data.github.repo.GitRetrofitUserRepoItem
import com.example.githuapiwithrxjavathreading.model.data.github.user.GitRetrofitUser
import com.example.githuapiwithrxjavathreading.utl.GitAPISelector
import com.example.githuapiwithrxjavathreading.view.adapter.RepoRecyclerDisplayAdapter
import com.example.githuapiwithrxjavathreading.viewmodel.ObjectViewModel

class UserDisplayFragment() : Fragment(), RepoRecyclerDisplayAdapter.GitAPIRepoDelegate {
    companion object {
        lateinit var userDisplayItemFragment: UserDisplayFragment
        fun getInstance(gitAPISelector: GitAPISelector, gitUserItem : GitRetrofitUser): UserDisplayFragment{
            if(!this::userDisplayItemFragment.isInitialized)// checking if lateinit property has been initialized
                userDisplayItemFragment = UserDisplayFragment()
            userDisplayItemFragment.setSelector(gitAPISelector)
            userDisplayItemFragment.setGitUserItem(gitUserItem)
            return userDisplayItemFragment
        }
    }

    private lateinit var binding: FragmentUserDisplayBinding
    private val adapter = RepoRecyclerDisplayAdapter(this)
    private lateinit var gitAPISelector : GitAPISelector
    private lateinit var gitUserItem : GitRetrofitUser

    fun setGitUserItem(gitUserItem : GitRetrofitUser){
        this.gitUserItem = gitUserItem
    }

     fun setSelector(gitAPISelector: GitAPISelector){
        this.gitAPISelector = gitAPISelector
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserDisplayBinding.inflate(inflater, container, false)
        binding.userListRecyclerview.adapter = adapter
        ObjectViewModel.instance.searchRepos(gitUserItem.login)
        ObjectViewModel.instance.gitRepoData.observe(viewLifecycleOwner, {
            adapter.apiList = it
            Glide.with(binding.root)
                .applyDefaultRequestOptions(RequestOptions().circleCrop())
                .load(it[0].owner.avatar_url)
                .into(binding.userImageview)
            binding.userNameTv.text = it[0].owner.login
        })

        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /*binding.searchUserButton.setOnClickListener{
            ObjectViewModel.instance.searchUser(binding.userNameEditText.text.toString())
            ObjectViewModel.instance.gitUsersData.observe(viewLifecycleOwner, {
                adapter.apiList = it
            })
        }*/
    }

    override fun selectItem(gitRetrofitItem: GitRetrofitUserRepoItem) {
        gitAPISelector.openRepoDetailsFragment(gitRetrofitItem)
    }
}