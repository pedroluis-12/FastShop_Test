package com.pedroluis.fastshoptest.di

import com.pedroluis.fastshoptest.features.catalog.viewmodel.CatalogViewModel
import com.pedroluis.fastshoptest.features.details.viewmodel.DetailViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { CatalogViewModel(repository = get()) }
    viewModel { DetailViewModel(repository = get()) }
}