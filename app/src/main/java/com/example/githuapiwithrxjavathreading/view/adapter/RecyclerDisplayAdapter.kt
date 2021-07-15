package com.example.githuapiwithrxjavathreading.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.githuapiwithrxjavathreading.databinding.CardGitResultItemDisplayLayoutBinding
import com.example.githuapiwithrxjavathreading.model.data.github.GitRetrofitItem

class RecyclerDisplayAdapter(): RecyclerView.Adapter<RecyclerDisplayAdapter.CardItemHolder>() {
    inner class CardItemHolder(val binding: CardGitResultItemDisplayLayoutBinding): RecyclerView.ViewHolder(binding.root)

    companion object {
        val instance  = RecyclerDisplayAdapter()
    }

    var apiList : List<GitRetrofitItem> = listOf()
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
        }
    }

    override fun getItemCount(): Int = apiList.size
}