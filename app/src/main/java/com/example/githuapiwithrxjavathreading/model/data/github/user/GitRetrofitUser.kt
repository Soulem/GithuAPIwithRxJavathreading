package com.example.githuapiwithrxjavathreading.model.data.github.user

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class GitRetrofitUser(
    val avatar_url: String,
    val bio: @RawValue Any,
    val blog: String,
    val company: @RawValue Any,
    val created_at: String,
    val email: @RawValue Any,
    val events_url: String,
    val followers: Int,
    val followers_url: String,
    val following: Int,
    val following_url: String,
    val gists_url: String,
    val gravatar_id: String,
    val hireable: @RawValue Any,
    val html_url: String,
    val id: Int,
    val location: @RawValue Any,
    val login: String,
    val name: @RawValue Any,
    val node_id: String,
    val organizations_url: String,
    val public_gists: Int,
    val public_repos: Int,
    val received_events_url: String,
    val repos_url: String,
    val site_admin: Boolean,
    val starred_url: String,
    val subscriptions_url: String,
    val twitter_username: @RawValue Any,
    val type: String,
    val updated_at: String,
    val url: String
): Parcelable