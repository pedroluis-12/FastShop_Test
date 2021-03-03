package com.pedroluis.fastshoptest.features.details.model

import com.google.gson.annotations.SerializedName

data class DetailProductionCompany(
    @SerializedName("id")
    val prodCompID: Int,
    @SerializedName("logo_path")
    val prodCompLogoPath: String?,
    @SerializedName("name")
    val prodCompName: String,
    @SerializedName("origin_country")
    val prodCompOriginCountry: String
)