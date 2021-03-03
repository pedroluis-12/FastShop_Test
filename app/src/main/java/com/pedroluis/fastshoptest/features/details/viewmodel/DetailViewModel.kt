package com.pedroluis.fastshoptest.features.details.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pedroluis.fastshoptest.features.details.viewmodel.state.DetailViewState
import com.pedroluis.fastshoptest.infrastructure.ApiResult
import com.pedroluis.fastshoptest.repository.DetailRepository
import kotlinx.coroutines.launch
import timber.log.Timber

class DetailViewModel(private val repository : DetailRepository) : ViewModel() {

    private val detailState: MutableLiveData<DetailViewState> = MutableLiveData()
    val detailViewState: LiveData<DetailViewState> = detailState

    fun fetchDetailMovie(movieId: Int) {
        detailState.value = DetailViewState.ShowLoading(true)
        viewModelScope.launch {
            try {
                when (val result = repository.getDetailMovie(movieId)) {
                    is ApiResult.Success -> {
                        detailState.value = DetailViewState.Success(result.resultSuccess)
                        Timber.tag("success").d(result.resultSuccess.detailTitle)
                    }
                    is ApiResult.Error -> {
                        detailState.value = DetailViewState.Error(message = result.message.toString())
                        Timber.tag("success").d(result.message)
                    }
                }
            } catch (e: Exception) {
                detailState.value = DetailViewState.Error(e, e.message.toString())
            }
        }
    }
}