package com.sage.cahekr.cakehrandroidapp.data

import com.sage.cahekr.cakehrandroidapp.data.repository.FilmRepositoryImpl
import com.sage.cahekr.cakehrandroidapp.data.service.FilmService
import com.sage.cahekr.cakehrandroidapp.domain.repository.FilmRepository
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

internal val dataModule = module {

    single {
        OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .build()
    }

    single {
        Retrofit.Builder()
            .client(get<OkHttpClient>())
            .baseUrl("https://swapi.dev/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single { get<Retrofit>().create(FilmService::class.java) }

    fun provideRepository(service: FilmService): FilmRepository {
        return FilmRepositoryImpl(service)
    }

    single {provideRepository(get()) }
}
