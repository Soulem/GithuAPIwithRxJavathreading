package com.example.githuapiwithrxjavathreading.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.githuapiwithrxjavathreading.databinding.CardGitResultItemDisplayLayoutBinding
import com.example.githuapiwithrxjavathreading.model.data.github.repo.GitRetrofitUserRepoItem

class RecyclerDisplayAdapter(private val delegate: GitAPIDelegate): RecyclerView.Adapter<RecyclerDisplayAdapter.CardItemHolder>() {
    inner class CardItemHolder(val binding: CardGitResultItemDisplayLayoutBinding): RecyclerView.ViewHolder(binding.root)

    interface GitAPIDelegate{
        fun selectItem(gitRetrofitItemItem: GitRetrofitUserRepoItem)
    }

    var apiList : List<GitRetrofitUserRepoItem> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardItemHolder {
        val binding = CardGitResultItemDisplayLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return CardItemHolder(binding);
    }

    override fun onBindViewHolder(holder: CardItemHolder, position: Int) {
        val item = apiList[position]
        holder.binding.apply {
            this.repoNameTextView.text = item.name
            this.repoNameTextView.setOnClickListener{
                delegate.selectItem(item)
            }
        }
    }

    override fun getItemCount(): Int = apiList.size
}