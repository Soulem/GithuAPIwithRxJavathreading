package com.example.githuapiwithrxjavathreading.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.githuapiwithrxjavathreading.databinding.CardCommitItemLayoutBinding
import com.example.githuapiwithrxjavathreading.databinding.CardUserItemLayoutBinding
import com.example.githuapiwithrxjavathreading.model.data.github.commit.GitRetrofitUserCommitItem

class CommitRecyclerDisplayAdapter(commitDelegate: GitAPICommitDelegate): RecyclerView.Adapter<CommitRecyclerDisplayAdapter.CommitCardItemHolder>() {
    inner class CommitCardItemHolder(val binding: CardCommitItemLayoutBinding): RecyclerView.ViewHolder(binding.root)

    interface GitAPICommitDelegate{
        fun selectItem(gitRetrofitItemItem: GitRetrofitUserCommitItem)
    }

    var apiList : List<GitRetrofitUserCommitItem> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommitCardItemHolder {
        val binding = CardCommitItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return CommitCardItemHolder(binding);
    }

    override fun onBindViewHolder(holder: CommitCardItemHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int = apiList.size
}