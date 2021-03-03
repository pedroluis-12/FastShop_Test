package com.pedroluis.fastshoptest.repository

import com.pedroluis.fastshoptest.BuildConfig
import com.pedroluis.fastshoptest.features.details.model.DetailResponse
import com.pedroluis.fastshoptest.infrastructure.Api
import com.pedroluis.fastshoptest.infrastructure.ApiResult
import retrofit2.Response

class DetailRepository(private val api: Api) {
    suspend fun getDetailMovie(movieId: Int): ApiResult<DetailResponse> {
        val response: Response<DetailResponse>
        return try {
            response = api.getDetailsMovie(
                movieId = movieId,
                api_key = BuildConfig.API_KEY,
                language = "en-US"
            )
            if (response.isSuccessful) {
                ApiResult.Success(response.body() as DetailResponse)
            } else {
                ApiResult.Error(null, response.message())
            }
        } catch (e: Exception) {
            ApiResult.Error(e, e.message)
        }
    }
}