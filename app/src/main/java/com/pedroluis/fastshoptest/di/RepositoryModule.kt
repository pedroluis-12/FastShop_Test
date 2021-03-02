package com.pedroluis.fastshoptest.di

import com.pedroluis.fastshoptest.repository.CatalogRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { CatalogRepository(get()) }
}