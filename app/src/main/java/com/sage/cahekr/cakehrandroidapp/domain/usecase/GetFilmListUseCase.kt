package com.sage.cahekr.cakehrandroidapp.domain.usecase

import com.sage.cahekr.cakehrandroidapp.domain.model.FilmDomainModel
import com.sage.cahekr.cakehrandroidapp.domain.repository.FilmRepository
import java.io.IOException

internal class GetFilmListUseCase(
    private val filmRepository: FilmRepository
) {

    sealed class Result {
        data class Success(val data: List<FilmDomainModel>) : Result()
        data class Error(val e: Throwable) : Result()
    }

    suspend fun execute(): Result {
        return try {
            Result.Success(filmRepository.getFilms())
        } catch (e: IOException) {
            Result.Error(e)
        }
    }
}
