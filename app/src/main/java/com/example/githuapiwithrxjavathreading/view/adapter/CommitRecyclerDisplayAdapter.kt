package com.example.githuapiwithrxjavathreading.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.githuapiwithrxjavathreading.databinding.CardCommitItemLayoutBinding
import com.example.githuapiwithrxjavathreading.model.data.github.commit.GitRetrofitUserCommitItem

class CommitRecyclerDisplayAdapter(private val commitDelegate: GitAPICommitDelegate): RecyclerView.Adapter<CommitRecyclerDisplayAdapter.CommitCardItemHolder>() {
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

    override fun onBindViewHolder(holderCommit: CommitCardItemHolder, position: Int) {
        val item = apiList[position]
        holderCommit.binding.apply {
            this.commitTitleTextView.text = item.commit.message
            this.userNameTextView.text = item.commit.author.name
            this.commitDateTextView.text = item.commit.author.date
            Glide.with(this.root)
                .applyDefaultRequestOptions(RequestOptions().circleCrop())
                .load(item.author.avatar_url)
                .into(this.userIconImageview)
            holderCommit.binding.root.setOnClickListener{
                commitDelegate.selectItem(item)
            }
        }
    }

    override fun getItemCount(): Int = apiList.size
}