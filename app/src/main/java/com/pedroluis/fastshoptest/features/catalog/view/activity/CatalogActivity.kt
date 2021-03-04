package com.pedroluis.fastshoptest.features.catalog.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.pedroluis.fastshoptest.R
import com.pedroluis.fastshoptest.features.catalog.view.fragment.CatalogCarouselFragment

class CatalogActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_catalog)
        setupViews()
    }

    private fun setupViews() {
        val fragmentTransaction = supportFragmentManager.beginTransaction()

        fragmentTransaction.add(R.id.catalog_container,
            CatalogCarouselFragment.newInstance(getString(R.string.title_action_value),
                getString(R.string.title_action_adventure)))

        fragmentTransaction.add(R.id.catalog_container,
            CatalogCarouselFragment.newInstance(getString(R.string.title_comedian_value),
                getString(R.string.title_comedian)))

        fragmentTransaction.add(R.id.catalog_container,
            CatalogCarouselFragment.newInstance(getString(R.string.title_documentary_value),
                getString(R.string.title_documentary)))

        fragmentTransaction.commit()
    }
}
