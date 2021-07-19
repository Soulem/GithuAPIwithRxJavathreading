package com.example.githuapiwithrxjavathreading.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.githuapiwithrxjavathreading.databinding.FragmentCommitDisplayBinding
import com.example.githuapiwithrxjavathreading.model.data.github.commit.GitRetrofitUserCommitItem
import com.example.githuapiwithrxjavathreading.model.data.github.repo.GitRetrofitUserRepoItem
import com.example.githuapiwithrxjavathreading.utl.GitAPISelector
import com.example.githuapiwithrxjavathreading.view.adapter.CommitRecyclerDisplayAdapter
import com.example.githuapiwithrxjavathreading.view.adapter.RepoRecyclerDisplayAdapter

class RepoDisplayFragment : Fragment(), CommitRecyclerDisplayAdapter.GitAPICommitDelegate {
    companion object {
        lateinit var repoDisplayItemFragment: RepoDisplayFragment
        const val RESULT_KEY = "REPO_RESULT_KEY"
        fun getInstance(gitAPIRetrofitItem: GitRetrofitUserRepoItem): RepoDisplayFragment{
            if(!this::repoDisplayItemFragment.isInitialized)// checking if lateinit property has been initialized
                repoDisplayItemFragment = RepoDisplayFragment()

            return repoDisplayItemFragment.also {
                it.arguments = Bundle().also { bnd ->
                    bnd.putParcelable(RESULT_KEY, gitAPIRetrofitItem)
                }
            }
        }
    }

    private lateinit var binding: FragmentCommitDisplayBinding
    private lateinit var gitAPISelector: GitAPISelector
    private val adapter = CommitRecyclerDisplayAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCommitDisplayBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun selectItem(gitRetrofitItemItem: GitRetrofitUserCommitItem) {
        TODO("Not yet implemented")
    }
}