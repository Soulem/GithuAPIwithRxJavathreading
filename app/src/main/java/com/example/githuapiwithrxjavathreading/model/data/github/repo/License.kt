package com.example.githuapiwithrxjavathreading.model.data.github.repo

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class License(
    val key: String,
    val name: String,
    val node_id: String,
    val spdx_id: String,
    val url: String
): Parcelable