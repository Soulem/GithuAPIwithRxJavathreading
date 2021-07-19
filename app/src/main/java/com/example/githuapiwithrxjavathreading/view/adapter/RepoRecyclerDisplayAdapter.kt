package com.example.githuapiwithrxjavathreading.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.githuapiwithrxjavathreading.databinding.CardGitResultItemDisplayLayoutBinding
import com.example.githuapiwithrxjavathreading.model.data.github.repo.GitRetrofitUserRepoItem

class RepoRecyclerDisplayAdapter(private val repoDelegate: GitAPIRepoDelegate): RecyclerView.Adapter<RepoRecyclerDisplayAdapter.RepoCardItemHolder>() {
    inner class RepoCardItemHolder(val binding: CardGitResultItemDisplayLayoutBinding): RecyclerView.ViewHolder(binding.root)

    interface GitAPIRepoDelegate{
        fun selectItem(gitRetrofitItemItem: GitRetrofitUserRepoItem)
    }

    var apiList : List<GitRetrofitUserRepoItem> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoCardItemHolder {
        val binding = CardGitResultItemDisplayLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return RepoCardItemHolder(binding);
    }

    override fun onBindViewHolder(holderRepo: RepoCardItemHolder, position: Int) {
        val item = apiList[position]
        holderRepo.binding.apply {
            this.repoNameTextView.text = item.name
            this.repoNameTextView.setOnClickListener{
                repoDelegate.selectItem(item)
            }
        }
    }

    override fun getItemCount(): Int = apiList.size
}