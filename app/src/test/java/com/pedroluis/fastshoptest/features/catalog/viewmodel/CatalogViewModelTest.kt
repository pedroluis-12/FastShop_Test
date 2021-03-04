package com.pedroluis.fastshoptest.features.catalog.viewmodel

import android.os.Looper
import androidx.arch.core.executor.ArchTaskExecutor
import androidx.lifecycle.Observer
import com.pedroluis.fastshoptest.features.catalog.model.CatalogResponse
import com.pedroluis.fastshoptest.features.catalog.repository.CatalogRepository
import com.pedroluis.fastshoptest.features.catalog.viewmodel.state.CatalogViewState
import com.pedroluis.fastshoptest.features.utils.test
import com.pedroluis.fastshoptest.infrastructure.ApiResult
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.mockkStatic
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CatalogViewModelTest {

    @MockK
    private lateinit var catalogLiveDataObserver: Observer<CatalogViewState>
    private lateinit var viewModel: CatalogViewModel
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
    fun test_catalog_viewModel() = test {
        var result: CatalogResponse? = null
        arrange {
            result = CatalogResponse(
                catalogPage = 1,
                catalogTotalResults = 1,
                catalogTotalPages = 1,
                catalogResults = listOf()
            )

            val repository = MockRepository(result = result as CatalogResponse)
            viewModel = CatalogViewModel(repository)
            viewModel.catalogViewState.observeForever(catalogLiveDataObserver)
        }

        act {
            viewModel.fetchMovies("12")
        }

        assert {
            Assert.assertEquals(viewModel.catalogViewState.value, result)
        }
    }
}

class MockRepository(private val result: CatalogResponse): CatalogRepository {
    override suspend fun getMovies(genres: String): ApiResult<CatalogResponse> {
        return ApiResult.Success(result)
    }
}