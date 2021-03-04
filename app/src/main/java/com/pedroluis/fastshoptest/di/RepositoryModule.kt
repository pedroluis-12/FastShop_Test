package com.pedroluis.fastshoptest.di

import com.pedroluis.fastshoptest.infrastructure.Api
import com.pedroluis.fastshoptest.features.catalog.repository.CatalogRepository
import com.pedroluis.fastshoptest.features.catalog.repository.CatalogRepositoryImpl
import com.pedroluis.fastshoptest.features.details.repository.DetailRepository
import com.pedroluis.fastshoptest.features.details.repository.DetailRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    fun provideCatalogRepository(api: Api): CatalogRepository = CatalogRepositoryImpl(api)
    single { provideCatalogRepository(api = get()) }

    fun provideDetailRepository(api: Api): DetailRepository = DetailRepositoryImpl(api)
    single { provideDetailRepository(api = get()) }
}
