package com.example.githuapiwithrxjavathreading.model.data.github.commit

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Tree(
    val sha: String,
    val url: String
): Parcelable