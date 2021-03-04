package com.pedroluis.fastshoptest.features.details.viewmodel

import androidx.lifecycle.Observer
import com.pedroluis.fastshoptest.features.details.model.DetailResponse
import com.pedroluis.fastshoptest.features.details.repository.DetailRepository
import com.pedroluis.fastshoptest.features.details.viewmodel.state.DetailViewState
import com.pedroluis.fastshoptest.features.utils.test
import com.pedroluis.fastshoptest.infrastructure.ApiResult
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import java.math.BigDecimal

@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {
    @Mock
    private lateinit var detailLiveDataObserver: Observer<DetailViewState>
    private lateinit var viewModel: DetailViewModel

    @Test
    fun test_detail_viewModel() = test {
        var result: DetailResponse? = null
        arrange {
            result = DetailResponse(
                detailAdult = false,
                detailBackdropPath = "",
                detailBelongsToCollection = null,
                detailBudget = 0,
                detailGenres = listOf(),
                detailHomepage = null,
                detailId = 0,
                detailImdbId = null,
                detailOriginalLanguage = "",
                detailOriginalTitle = "",
                detailOverview = null,
                detailPopularity = BigDecimal.ZERO,
                detailPosterPath = null,
                detailProductCompanies = listOf(),
                detailProductCountries = listOf(),
                detailReleaseDate = "",
                detailRevenue = 0,
                detailRuntime = null,
                detailSpokenLanguages = listOf(),
                detailStatus = "",
                detailTagline = null,
                detailTitle = "",
                detailVideo = false,
                detailVoteAverage = BigDecimal.ZERO,
                detailVoteCount = 0
            )

            val repository = MockDetailRepository(result = result as DetailResponse)
            val viewModel = DetailViewModel(repository)
            viewModel.detailViewState.observeForever(detailLiveDataObserver)
        }

        act {
            viewModel.fetchDetailMovie(0)
        }

        assert {
            assertEquals(viewModel.detailViewState.value, result)
        }
    }
}

class MockDetailRepository(private val result: DetailResponse): DetailRepository {
    override suspend fun getDetailMovie(movieId: Int): ApiResult<DetailResponse> {
        return ApiResult.Success(result)
    }
}
