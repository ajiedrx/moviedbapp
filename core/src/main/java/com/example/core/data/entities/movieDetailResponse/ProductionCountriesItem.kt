package com.example.core.data.entities.movieDetailResponse

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProductionCountriesItem(
    val iso31661: String? = null,
    val name: String? = null,
) : Parcelable