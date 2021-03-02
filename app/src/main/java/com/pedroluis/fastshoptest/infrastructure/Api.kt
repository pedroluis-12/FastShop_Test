package com.pedroluis.fastshoptest.infrastructure

import com.pedroluis.fastshoptest.features.catalog.model.CatalogResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("/3/discover/movie")
    suspend fun getMovies(
        @Query("api_key") api_key: String,
        @Query("language") language: String,
        @Query("sort_by") sortBy: String,
        @Query("include_adult") adult: Boolean,
        @Query("include_video") video: Boolean,
        @Query("page") page: Int,
        @Query("with_genres") genres: String
    ): Response<CatalogResponse>
}
