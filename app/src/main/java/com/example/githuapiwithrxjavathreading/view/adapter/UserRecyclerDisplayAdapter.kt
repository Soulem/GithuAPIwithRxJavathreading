package com.example.githuapiwithrxjavathreading.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
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

    override fun onBindViewHolder(holderUser: UserCardItemHolder, position: Int) {
        val item = apiList[position]
        holderUser.binding.apply {
            this.userNameTv.text = item.login
            this.repoCountTv.text = item.public_repos.toString()
            Glide.with(holderUser.binding.root)
                .applyDefaultRequestOptions(RequestOptions().circleCrop())
                .load(item.avatar_url)
                .into(holderUser.binding.userProfileImage)

            holderUser.binding.root.setOnClickListener{
                userDelegate.selectItem(item)
            }
        }
    }

    override fun getItemCount(): Int = apiList.size
}