package com.pedroluis.fastshoptest.di

import com.pedroluis.fastshoptest.repository.CatalogRepository
import com.pedroluis.fastshoptest.repository.DetailRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { CatalogRepository(api = get()) }
    single { DetailRepository(api = get()) }
}
