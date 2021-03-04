package com.pedroluis.fastshoptest.features.details.viewmodel

import android.os.Looper
import androidx.arch.core.executor.ArchTaskExecutor
import androidx.lifecycle.Observer
import com.pedroluis.fastshoptest.features.details.model.DetailResponse
import com.pedroluis.fastshoptest.features.details.repository.DetailRepository
import com.pedroluis.fastshoptest.features.details.viewmodel.state.DetailViewState
import com.pedroluis.fastshoptest.features.utils.test
import com.pedroluis.fastshoptest.infrastructure.ApiResult
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.mockkStatic
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.math.BigDecimal

class DetailViewModelTest {
    @MockK
    private lateinit var detailLiveDataObserver: Observer<DetailViewState>
    private lateinit var viewModel: DetailViewModel
    private val mockLooper = mockk<Looper>()
    private val mockExecutor = mockk<ArchTaskExecutor>()

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        mockkStatic("android.os.Looper")
        mockkStatic("android.arch.core.executor.ArchTaskExecutor")
        every { Looper.getMainLooper() } returns mockLooper
        every { mockLooper.thread } returns Thread()
        every { ArchTaskExecutor.getInstance() } returns mockExecutor
        every { mockExecutor.isMainThread } returns true
    }

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
            viewModel = DetailViewModel(repository)
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
