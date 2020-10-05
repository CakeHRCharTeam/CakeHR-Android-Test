package com.sage.cahekr.cakehrandroidapp.domain.repository

import com.sage.cahekr.cakehrandroidapp.domain.model.FilmDomainModel

internal interface FilmRepository {

    suspend fun getFilms(): List<FilmDomainModel>
}
