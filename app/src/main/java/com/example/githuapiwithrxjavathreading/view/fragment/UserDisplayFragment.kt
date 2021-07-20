package com.example.githuapiwithrxjavathreading.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.githuapiwithrxjavathreading.databinding.FragmentCommitDisplayBinding
import com.example.githuapiwithrxjavathreading.databinding.FragmentUserDisplayBinding
import com.example.githuapiwithrxjavathreading.model.data.github.repo.GitRetrofitUserRepoItem
import com.example.githuapiwithrxjavathreading.model.data.github.user.GitRetrofitUser
import com.example.githuapiwithrxjavathreading.utl.GitAPISelector
import com.example.githuapiwithrxjavathreading.view.adapter.RepoRecyclerDisplayAdapter

class UserDisplayFragment : Fragment(), RepoRecyclerDisplayAdapter.GitAPIRepoDelegate {
    companion object {
        lateinit var userDisplayItemFragment: UserDisplayFragment
        const val RESULT_KEY = "REPO_RESULT_KEY"
        fun getInstance(gitAPIRetrofitItem: GitRetrofitUser): UserDisplayFragment{
            if(!this::userDisplayItemFragment.isInitialized)// checking if lateinit property has been initialized
                userDisplayItemFragment = UserDisplayFragment()

            return userDisplayItemFragment.also {
                it.arguments = Bundle().also { bnd ->
                    bnd.putParcelable(RESULT_KEY, gitAPIRetrofitItem)
                }
            }
        }
    }

    private lateinit var binding: FragmentUserDisplayBinding
    private lateinit var gitAPISelector: GitAPISelector
    private val adapter = RepoRecyclerDisplayAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserDisplayBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun selectItem(gitRetrofitItemItem: GitRetrofitUserRepoItem) {
        TODO("Not yet implemented")
    }
}