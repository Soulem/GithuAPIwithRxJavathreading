package com.example.githuapiwithrxjavathreading.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.githuapiwithrxjavathreading.databinding.CardUserItemLayoutBinding
import com.example.githuapiwithrxjavathreading.model.data.github.user.GitRetrofitUser

class UserRecyclerDisplayAdapter(private val userDelegate : GitAPIUserDelegate): RecyclerView.Adapter<UserRecyclerDisplayAdapter.UserCardItemHolder>() {
    inner class UserCardItemHolder(val binding: CardUserItemLayoutBinding): RecyclerView.ViewHolder(binding.root)

    interface GitAPIUserDelegate{
        fun selectItem(gitRetrofitItemItem: GitRetrofitUser)
    }

    var apiList : List<GitRetrofitUser> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserCardItemHolder {
        val binding = CardUserItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return UserCardItemHolder(binding);
    }

    override fun onBindViewHolder(holder: UserCardItemHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int = apiList.size
}