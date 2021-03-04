package com.pedroluis.fastshoptest.features.details.repository

import com.pedroluis.fastshoptest.features.details.model.DetailResponse
import com.pedroluis.fastshoptest.infrastructure.ApiResult

interface DetailRepository {
    suspend fun getDetailMovie(movieId: Int): ApiResult<DetailResponse>
}