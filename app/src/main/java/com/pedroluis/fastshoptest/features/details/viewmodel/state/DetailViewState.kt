package com.pedroluis.fastshoptest.features.details.viewmodel.state

import com.pedroluis.fastshoptest.features.details.model.DetailResponse
import java.lang.Exception

sealed class DetailViewState {
    data class ShowLoading(val visibility: Boolean): DetailViewState()
    data class Success(val detailResult: DetailResponse): DetailViewState()
    data class Error(val exception: Exception? = null, val message: String): DetailViewState()
}