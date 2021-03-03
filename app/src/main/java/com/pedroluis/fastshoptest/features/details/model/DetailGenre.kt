package com.pedroluis.fastshoptest.features.details.model

import com.google.gson.annotations.SerializedName

data class DetailGenre(
    @SerializedName("id")
    val genreId: Int,
    @SerializedName("name")
    val genreName: String
)