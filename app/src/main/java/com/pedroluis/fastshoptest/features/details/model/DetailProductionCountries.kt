package com.pedroluis.fastshoptest.features.details.model

import com.google.gson.annotations.SerializedName

data class DetailProductionCountries(
    @SerializedName("iso_3166_1")
    val prodCountriesIso: String,
    @SerializedName("name")
    val prodCountriesName: String
)