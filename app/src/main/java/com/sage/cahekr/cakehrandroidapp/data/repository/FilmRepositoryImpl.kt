package com.sage.cahekr.cakehrandroidapp.data.repository

import com.sage.cahekr.cakehrandroidapp.data.model.toDomainModel
import com.sage.cahekr.cakehrandroidapp.data.service.FilmService
import com.sage.cahekr.cakehrandroidapp.domain.model.FilmDomainModel
import com.sage.cahekr.cakehrandroidapp.domain.repository.FilmRepository

internal class FilmRepositoryImpl(private val filmService: FilmService) : FilmRepository {

    override suspend fun getFilms(): List<FilmDomainModel> {
        return filmService.films()
            .results
            .map { it.toDomainModel() }
    }
}
