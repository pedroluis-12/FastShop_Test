package com.pedroluis.fastshoptest.features.details.model

import com.google.gson.annotations.SerializedName
import java.math.BigDecimal

data class DetailResponse(
    @SerializedName("adult")
    val detailAdult: Boolean,
    @SerializedName("backdrop_path")
    val detailBackdropPath: String,
    @SerializedName("belongs_to_collection")
    val detailBelongsToCollection: Any?,
    @SerializedName("budget")
    val detailBudget: Int,
    @SerializedName("genres")
    val detailGenres: List<DetailGenre>,
    @SerializedName("homepage")
    val detailHomepage: String?,
    @SerializedName("id")
    val detailId: Int,
    @SerializedName("imdb_id")
    val detailImdbId: String?,
    @SerializedName("original_language")
    val detailOriginalLanguage: String,
    @SerializedName("original_title")
    val detailOriginalTitle: String,
    @SerializedName("overview")
    val detailOverview: String?,
    @SerializedName("popularity")
    val detailPopularity: BigDecimal,
    @SerializedName("poster_path")
    val detailPosterPath: String?,
    @SerializedName("production_companies")
    val detailProductCompanies: List<DetailProductionCompany>,
    @SerializedName("production_countries")
    val detailProductCountries: List<DetailProductionCountries>,
    @SerializedName("release_date")
    val detailReleaseDate: String,
    @SerializedName("revenue")
    val detailRevenue: Int,
    @SerializedName("runtime")
    val detailRuntime: Int?,
    @SerializedName("spoken_languages")
    val detailSpokenLanguages: List<DetailSpokenLanguage>,
    @SerializedName("status")
    val detailStatus: String,
    @SerializedName("tagline")
    val detailTagline: String?,
    @SerializedName("title")
    val detailTitle: String?,
    @SerializedName("video")
    val detailVideo: Boolean,
    @SerializedName("vote_average")
    val detailVoteAverage: BigDecimal,
    @SerializedName("vote_count")
    val detailVoteCount: Int
)