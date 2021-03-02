package com.pedroluis.fastshoptest.di

import com.pedroluis.fastshoptest.features.catalog.viewmodel.CatalogViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        CatalogViewModel(repository = get())
    }
}