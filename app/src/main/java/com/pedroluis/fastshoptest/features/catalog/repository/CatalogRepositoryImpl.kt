package com.pedroluis.fastshoptest.features.catalog.repository

import com.pedroluis.fastshoptest.BuildConfig
import com.pedroluis.fastshoptest.features.catalog.model.CatalogResponse
import com.pedroluis.fastshoptest.features.catalog.repository.CatalogRepository
import com.pedroluis.fastshoptest.infrastructure.Api
import com.pedroluis.fastshoptest.infrastructure.ApiResult
import retrofit2.Response

class CatalogRepositoryImpl(private val api: Api):
    CatalogRepository {
    override suspend fun getMovies(genres: String): ApiResult<CatalogResponse> {
        val response: Response<CatalogResponse>
        return try {
            response = api.getMovies(
                    api_key = BuildConfig.API_KEY,
                    adult = false,
                    genres = genres,
                    language = "en-US",
                    page = 1,
                    sortBy = "popularity.asc",
                    video = false
            )
            if (response.isSuccessful) {
                ApiResult.Success(response.body() as CatalogResponse)
            } else {
                ApiResult.Error(null, response.message())
            }
        } catch (e: Exception) {
            ApiResult.Error(e, e.message)
        }
    }
}