package com.pedroluis.fastshoptest.features.catalog.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pedroluis.fastshoptest.R
import com.pedroluis.fastshoptest.features.catalog.model.CatalogResult
import com.pedroluis.fastshoptest.features.catalog.view.adapter.CatalogAdapter
import com.pedroluis.fastshoptest.features.catalog.viewmodel.CatalogViewModel
import com.pedroluis.fastshoptest.features.catalog.viewmodel.state.CatalogViewState
import com.pedroluis.fastshoptest.utils.bindView
import com.pedroluis.fastshoptest.utils.onClick
import org.koin.android.viewmodel.ext.android.viewModel
import timber.log.Timber

class CatalogCarouselFragment : Fragment() {

    private val catalogList: RecyclerView by bindView(R.id.catalog_movies_list)
    private val catalogTitle: TextView by bindView(R.id.catalog_title)
    private val catalogTitleContainer: ConstraintLayout by bindView(R.id.catalog_title_container)
    private val catalogViewModel: CatalogViewModel by viewModel()
    private lateinit var genres: String
    private lateinit var title: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            genres = it.getString(GENRES) ?: "12"
            title = it.getString(TITLE) ?: "Título indisponível"
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_catalog_carousel, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObserver()
        catalogViewModel.fetchMovies(genres)
    }

    private fun setupRecycler(listMovies: List<CatalogResult>) {
        catalogTitle.text = title
        catalogTitleContainer.onClick {
            Toast.makeText(context, title, Toast.LENGTH_LONG).show()
        }
        catalogList.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = CatalogAdapter(listMovies, context)
        }
    }

    private fun setupObserver() {
        catalogViewModel.catalogViewState.observe(viewLifecycleOwner, Observer { viewState ->
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
                        Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
                    }
                }
            }
        })
    }

    companion object {
        private const val GENRES = "genres"
        private const val TITLE = "title"

        @JvmStatic
        fun newInstance(genres: String, title: String) =
            CatalogCarouselFragment()
                .apply {
                arguments = Bundle().apply {
                    putString(GENRES, genres)
                    putString(TITLE, title)
                }
            }
    }
}
