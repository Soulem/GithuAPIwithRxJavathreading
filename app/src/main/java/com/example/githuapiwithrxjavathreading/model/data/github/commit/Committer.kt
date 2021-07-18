package com.example.githuapiwithrxjavathreading.model.data.github.commit

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Committer(
    val date: String,
    val email: String,
    val name: String
): Parcelable