package com.example.githuapiwithrxjavathreading.model.data.github.commit

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class GitRetrofitUserCommitItem(
    val author: Author,
    val comments_url: String,
    val commit: Commit,
    val committer: CommitterX,
    val html_url: String,
    val node_id: String,
    val parents: @RawValue List<Any>,
    val sha: String,
    val url: String
): Parcelable