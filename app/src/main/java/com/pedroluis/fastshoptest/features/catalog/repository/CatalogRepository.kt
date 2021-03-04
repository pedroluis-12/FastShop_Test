package com.pedroluis.fastshoptest.features.catalog.repository

import com.pedroluis.fastshoptest.features.catalog.model.CatalogResponse
import com.pedroluis.fastshoptest.infrastructure.ApiResult

interface CatalogRepository {
    suspend fun getMovies(genres: String): ApiResult<CatalogResponse>
}