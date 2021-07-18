package com.example.githuapiwithrxjavathreading.model.data.github.commit

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Commit(
    val author: AuthorX,
    val comment_count: Int,
    val committer: Committer,
    val message: String,
    val tree: Tree,
    val url: String,
    val verification: Verification
): Parcelable