package com.example.githuapiwithrxjavathreading.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.githuapiwithrxjavathreading.databinding.FragmentCommitDisplayBinding
import com.example.githuapiwithrxjavathreading.model.data.github.commit.GitRetrofitUserCommitItem
import com.example.githuapiwithrxjavathreading.utl.GitAPISelector

class CommitDisplayFragment : Fragment() {
    companion object {
        lateinit var commitDisplayItemFragment: CommitDisplayFragment
        const val RESULT_KEY = "COMMIT_RESULT_KEY"
        fun getInstance(gitAPIRetrofitItem: GitRetrofitUserCommitItem, gitAPISelector: GitAPISelector): CommitDisplayFragment{
            if(!this::commitDisplayItemFragment.isInitialized)// checking if lateinit property has been initialized
                commitDisplayItemFragment = CommitDisplayFragment()
            commitDisplayItemFragment.setSelector(gitAPISelector)
            commitDisplayItemFragment.setGitRetrofitCommitItem(gitAPIRetrofitItem)
            return commitDisplayItemFragment.also {
                it.arguments = Bundle().also { bnd ->
                    bnd.putParcelable(RESULT_KEY, gitAPIRetrofitItem)
                }
            }
        }
    }

    private lateinit var binding: FragmentCommitDisplayBinding
    private lateinit var gitAPISelector: GitAPISelector
    private lateinit var gitRetrofitCommitItem: GitRetrofitUserCommitItem

    fun setGitRetrofitCommitItem(gitRerofitCommitItem: GitRetrofitUserCommitItem){
        this.gitRetrofitCommitItem = gitRerofitCommitItem
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
        binding = FragmentCommitDisplayBinding.inflate(inflater, container, false)
        binding.userNameTextView.text = gitRetrofitCommitItem.commit.author.name

        val tempString = gitRetrofitCommitItem.commit.author.date
        val newDate = tempString.subSequence(5, 7).toString() +"-"+ tempString.subSequence(8, 10).toString()+ "-"+tempString.subSequence(0, 4).toString()

        Glide.with(binding.root)
            .applyDefaultRequestOptions(RequestOptions().circleCrop())
            .load(gitRetrofitCommitItem.author.avatar_url)
            .into(binding.userIconImageview)

        binding.commitDateTextView.text = newDate
        val tempArray = gitRetrofitCommitItem.commit.message.split("\n\n")

        val title = tempArray[0]
        val descriptor : String

        if (2 <= tempArray.size)
            descriptor = tempArray[1]

        binding.commitTitleTextView.text = title

        // Inflate the layout for this fragment
        return binding.root
    }
}