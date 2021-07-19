package com.example.githuapiwithrxjavathreading.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.githuapiwithrxjavathreading.R
import com.example.githuapiwithrxjavathreading.databinding.FragmentCommitDisplayBinding
import com.example.githuapiwithrxjavathreading.databinding.FragmentDisplayItemBinding
import com.example.githuapiwithrxjavathreading.model.data.github.commit.GitRetrofitUserCommitItem
import com.example.githuapiwithrxjavathreading.model.data.github.repo.GitRetrofitUserRepoItem
import com.example.githuapiwithrxjavathreading.utl.GitAPISelector
import com.example.githuapiwithrxjavathreading.view.adapter.RepoRecyclerDisplayAdapter

class CommitDisplayFragment : Fragment() {
    companion object {
        lateinit var commitDisplayItemFragment: CommitDisplayFragment
        const val RESULT_KEY = "COMMIT_RESULT_KEY"
        fun getInstance(gitAPIRetrofitItem: GitRetrofitUserCommitItem): CommitDisplayFragment{
            if(!this::commitDisplayItemFragment.isInitialized)// checking if lateinit property has been initialized
                commitDisplayItemFragment = CommitDisplayFragment()

            return commitDisplayItemFragment.also {
                it.arguments = Bundle().also { bnd ->
                    bnd.putParcelable(RESULT_KEY, gitAPIRetrofitItem)
                }
            }
        }
    }

    private lateinit var binding: FragmentCommitDisplayBinding
    //private lateinit var gitAPISelector: GitAPISelector

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
}