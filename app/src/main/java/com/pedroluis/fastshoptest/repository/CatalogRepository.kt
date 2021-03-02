package com.pedroluis.fastshoptest.repository

import com.pedroluis.fastshoptest.BuildConfig
import com.pedroluis.fastshoptest.features.catalog.model.CatalogResponse
import com.pedroluis.fastshoptest.infrastructure.Api
import com.pedroluis.fastshoptest.infrastructure.ApiResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CatalogRepository(private val api: Api) {
    suspend fun getMoviesRepo(): ApiResult<CatalogResponse> {
        val response = api.getMovies(
            api_key = BuildConfig.API_KEY,
            adult = false,
            genres = "",
            language = "pt-BR",
            page = 1,
            sortBy = "popularity.asc",
            video = false
        )
        response.body()?.let {
            withContext(Dispatchers.IO) {  }
        }
        return if (response.isSuccessful) {
            ApiResult.Success(response.body() as CatalogResponse)
        } else {
            ApiResult.Error(null, response.message())
        }
    }
}