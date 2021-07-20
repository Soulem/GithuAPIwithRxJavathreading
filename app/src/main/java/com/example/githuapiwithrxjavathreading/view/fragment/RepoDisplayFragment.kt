package com.example.githuapiwithrxjavathreading.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.githuapiwithrxjavathreading.databinding.FragmentRepoDisplayBinding
import com.example.githuapiwithrxjavathreading.model.data.github.commit.GitRetrofitUserCommitItem
import com.example.githuapiwithrxjavathreading.model.data.github.repo.GitRetrofitUserRepoItem
import com.example.githuapiwithrxjavathreading.utl.GitAPISelector
import com.example.githuapiwithrxjavathreading.view.adapter.CommitRecyclerDisplayAdapter
import com.example.githuapiwithrxjavathreading.viewmodel.ObjectViewModel

class RepoDisplayFragment() : Fragment(), CommitRecyclerDisplayAdapter.GitAPICommitDelegate {
    companion object {
        lateinit var repoDisplayItemFragment: RepoDisplayFragment
        const val RESULT_KEY = "REPO_RESULT_KEY"
        fun getInstance(gitAPISelector: GitAPISelector, gitRepoItem: GitRetrofitUserRepoItem): RepoDisplayFragment{
            if(!this::repoDisplayItemFragment.isInitialized)// checking if lateinit property has been initialized
                repoDisplayItemFragment = RepoDisplayFragment()

            repoDisplayItemFragment.setGitRepoItem(gitRepoItem)
            repoDisplayItemFragment.setGitAPISelector(gitAPISelector)

            return repoDisplayItemFragment
        }
    }

    private lateinit var binding: FragmentRepoDisplayBinding
    private lateinit var gitRepoItem: GitRetrofitUserRepoItem
    private val adapter = CommitRecyclerDisplayAdapter(this)
    private lateinit var gitAPISelector: GitAPISelector

    fun setGitAPISelector(gitAPISelector: GitAPISelector){
        this.gitAPISelector = gitAPISelector
    }

    fun setGitRepoItem(gitRepoItem: GitRetrofitUserRepoItem){
        this.gitRepoItem = gitRepoItem
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRepoDisplayBinding.inflate(inflater, container, false)
        binding.commitListRecyclerview.adapter = adapter
        ObjectViewModel.instance.gitCommitData.observe(viewLifecycleOwner, {
            adapter.apiList = it

            val tempString = it[0].commit.author.date
            val newDate = tempString.subSequence(5, 6).toString() +"-"+ tempString.subSequence(8, 9).toString()+ "-"+tempString.subSequence(0, 3).toString()

            binding.repoDateTv.text = newDate
            binding.repoLinkTv.text = gitRepoItem.html_url
            binding.repoNameTv.text = gitRepoItem.owner.login

        })
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun selectItem(gitRetrofitItemItem: GitRetrofitUserCommitItem) {
        gitAPISelector.openCommitDetailsFragment(gitRetrofitItemItem)
    }
}