package com.pedroluis.fastshoptest.features.catalog.viewmodel.state

import com.pedroluis.fastshoptest.features.catalog.model.CatalogResult
import java.lang.Exception

sealed class CatalogViewState {
    data class ShowLoading(val visibility: Boolean): CatalogViewState()
    data class Success(val catalogResults: List<CatalogResult>): CatalogViewState()
    data class Error(val exception: Exception? = null, val message: String): CatalogViewState()
}
