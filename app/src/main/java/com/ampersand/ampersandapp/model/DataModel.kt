package com.ampersand.ampersandapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DataModel(
    val id: Int,
    val name: String,
    val data: Map<String, String>?
) : Parcelable
