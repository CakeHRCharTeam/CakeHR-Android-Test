package com.sage.cahekr.cakehrandroidapp.domain

import com.sage.cahekr.cakehrandroidapp.domain.usecase.GetFilmListUseCase
import org.koin.dsl.module

internal val domainModule = module {
    factory { GetFilmListUseCase(get()) }
}
