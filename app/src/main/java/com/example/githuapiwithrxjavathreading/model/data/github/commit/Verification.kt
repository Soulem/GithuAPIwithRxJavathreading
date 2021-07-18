package com.example.githuapiwithrxjavathreading.model.data.github.commit

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class Verification(
    val payload: @RawValue Any,
    val reason: String,
    val signature: @RawValue Any,
    val verified: Boolean
): Parcelable