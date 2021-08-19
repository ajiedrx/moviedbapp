package com.example.core.data.entities.movieDetailResponse

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GenresItem(
    val name: String? = null,
    val id: Int? = null,
) : Parcelable