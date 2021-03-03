package com.pedroluis.fastshoptest.features.catalog.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pedroluis.fastshoptest.features.catalog.viewmodel.state.CatalogViewState
import com.pedroluis.fastshoptest.infrastructure.ApiResult
import com.pedroluis.fastshoptest.repository.CatalogRepository
import kotlinx.coroutines.launch
import timber.log.Timber

class CatalogViewModel(private val repository : CatalogRepository) : ViewModel() {

    private val catalogState: MutableLiveData<CatalogViewState> = MutableLiveData()
    val catalogViewState: LiveData<CatalogViewState> = catalogState

    fun fetchMovies() {
        catalogState.value = CatalogViewState.ShowLoading(true)
        viewModelScope.launch {
            try {
                when (val result = repository.getMovies()) {
                    is ApiResult.Success -> {
                        catalogState.value =
                            CatalogViewState.Success(result.resultSuccess)
                        Timber.tag("success")
                            .d(result.resultSuccess.catalogTotalResults.toString())
                    }
                    is ApiResult.Error -> {
                        catalogState.value =
                            CatalogViewState.Error(message = result.message.toString())
                        Timber.tag("success")
                            .d(result.message)
                    }
                }
            } catch (e: Exception) {
                catalogState.value =
                    CatalogViewState.Error(e, e.message.toString())
            }
        }
    }
}
