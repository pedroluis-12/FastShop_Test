package com.pedroluis.fastshoptest.features.details.view.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import com.pedroluis.fastshoptest.R
import com.pedroluis.fastshoptest.features.details.model.DetailGenre
import com.pedroluis.fastshoptest.features.details.model.DetailResponse
import com.pedroluis.fastshoptest.features.details.viewmodel.DetailViewModel
import com.pedroluis.fastshoptest.features.details.viewmodel.state.DetailViewState
import com.pedroluis.fastshoptest.utils.bindBundle
import com.pedroluis.fastshoptest.utils.bindView
import com.pedroluis.fastshoptest.utils.loadImage
import org.koin.android.viewmodel.ext.android.viewModel
import timber.log.Timber

class DetailActivity : AppCompatActivity() {

    private val detailPoster: ImageView by bindView(R.id.detail_poster)
    private val detailTitle: TextView by bindView(R.id.detail_title)
    private val detailDescription: TextView by bindView(R.id.detail_description)
    private val detailGenreValue: TextView by bindView(R.id.detail_genre_value)

    private val detailViewModel: DetailViewModel by viewModel()
    private val movieId: Int by bindBundle(MOVIE_ID)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        setupObserver()
        detailViewModel.fetchDetailMovie(movieId)
    }

    private fun setupViews(detailResponse: DetailResponse) {
        if (detailResponse.detailTitle.isNullOrEmpty() ||
            detailResponse.detailOverview.isNullOrEmpty() ||
            detailResponse.detailGenres.isNullOrEmpty()) {
            Toast.makeText(this, getString(R.string.empty_message),
                Toast.LENGTH_LONG).show()
            finish()
        }

        detailPoster.loadImage(detailResponse.detailPosterPath)
        detailTitle.text = detailResponse.detailTitle
        detailDescription.text = detailResponse.detailOverview
        detailGenreValue.text = setupGenres(detailResponse.detailGenres)
    }

    private fun setupGenres(listGenres: List<DetailGenre>): String {
        val genreNames: MutableList<String> = mutableListOf()
        listGenres.forEach { genreNames.add(it.genreName) }
        return genreNames.joinToString()
    }

    private fun setupObserver() {
        detailViewModel.detailViewState.observe(this, Observer { viewState ->
            viewState.let {
                when (it) {
                    is DetailViewState.ShowLoading -> {
                        Timber.tag("loading").i(it.visibility.toString())
                    }
                    is DetailViewState.Success -> {
                        setupViews(it.detailResult)
                    }
                    is DetailViewState.Error -> {
                        Timber.tag("error").i(it.message)
                        Toast.makeText(this, getString(R.string.empty_message),
                            Toast.LENGTH_LONG).show()
                        finish()
                    }
                }
            }
        })
    }

    companion object {
        private const val MOVIE_ID = "movie_id"

        fun getLaunchIntent(context: Context, movieId: Int): Intent =
            Intent(context, DetailActivity::class.java).apply {
                putExtra(MOVIE_ID, movieId)
            }
    }
}
