package com.pedroluis.fastshoptest.features.details.model

import com.google.gson.annotations.SerializedName

data class DetailSpokenLanguage(
    @SerializedName("english_name")
    val spokenLangEngName: String,
    @SerializedName("iso_639_1")
    val spokenLangIso: String,
    @SerializedName("name")
    val spokenLangName: String
)