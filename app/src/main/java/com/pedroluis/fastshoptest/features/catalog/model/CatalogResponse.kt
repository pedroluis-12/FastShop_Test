package com.pedroluis.fastshoptest.features.catalog.model

import com.google.gson.annotations.SerializedName

data class CatalogResponse(
    @SerializedName("page")
    val catalogPage: Int,
    @SerializedName("results")
    val catalogResults: List<CatalogResult>,
    @SerializedName("total_pages")
    val catalogTotalPages: Int,
    @SerializedName("total_results")
    val catalogTotalResults: Int
)