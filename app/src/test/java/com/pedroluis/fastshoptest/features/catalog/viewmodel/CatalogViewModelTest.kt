package com.pedroluis.fastshoptest.features.catalog.viewmodel

import androidx.lifecycle.Observer
import com.pedroluis.fastshoptest.features.catalog.model.CatalogResponse
import com.pedroluis.fastshoptest.features.catalog.repository.CatalogRepository
import com.pedroluis.fastshoptest.features.catalog.viewmodel.state.CatalogViewState
import com.pedroluis.fastshoptest.features.utils.test
import com.pedroluis.fastshoptest.infrastructure.ApiResult
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CatalogViewModelTest {

    @Mock
    private lateinit var catalogLiveDataObserver: Observer<CatalogViewState>
    private lateinit var viewModel: CatalogViewModel

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
            val viewModel = CatalogViewModel(repository)
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