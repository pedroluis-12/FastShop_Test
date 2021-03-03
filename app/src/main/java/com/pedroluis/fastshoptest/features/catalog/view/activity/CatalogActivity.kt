package com.pedroluis.fastshoptest.features.catalog.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pedroluis.fastshoptest.R
import com.pedroluis.fastshoptest.features.catalog.model.CatalogResult
import com.pedroluis.fastshoptest.features.catalog.view.adapter.CatalogAdapter
import com.pedroluis.fastshoptest.features.catalog.viewmodel.CatalogViewModel
import com.pedroluis.fastshoptest.features.catalog.viewmodel.state.CatalogViewState
import com.pedroluis.fastshoptest.utils.bindView
import org.koin.android.viewmodel.ext.android.viewModel
import timber.log.Timber

class CatalogActivity : AppCompatActivity() {

    private val catalogList: RecyclerView by bindView(R.id.catalog_movies_list)
    private val catalogViewModel: CatalogViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_catalog)

        setupObserver()
        catalogViewModel.fetchMovies()
    }

    private fun setupRecycler(listMovies: List<CatalogResult>) {
        catalogList.apply {
            layoutManager = LinearLayoutManager(this@CatalogActivity,
                LinearLayoutManager.HORIZONTAL, false)
            adapter = CatalogAdapter(listMovies, this@CatalogActivity)
        }
    }

    private fun setupObserver() {
        catalogViewModel.catalogViewState.observe(this, Observer { viewState ->
            viewState.let {
                when (it) {
                    is CatalogViewState.ShowLoading -> {
                        Timber.tag("loading").i(it.visibility.toString())
                    }
                    is CatalogViewState.Success -> {
                        setupRecycler(it.catalogResponse.catalogResults)
                    }
                    is CatalogViewState.Error -> {
                        Timber.tag("error").i(it.message)
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                }
            }
        })
    }
}