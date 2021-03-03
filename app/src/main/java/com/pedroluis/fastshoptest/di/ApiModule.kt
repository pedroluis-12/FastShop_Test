package com.pedroluis.fastshoptest.di

import com.pedroluis.fastshoptest.infrastructure.Api
import org.koin.dsl.module
import retrofit2.Retrofit

val apiModule = module {
    fun provideApi(retrofit: Retrofit): Api {
        return retrofit.create(Api::class.java)
    }
    single { provideApi(retrofit = get()) }
}
