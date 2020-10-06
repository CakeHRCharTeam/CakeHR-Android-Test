package com.sage.cahekr.cakehrandroidapp.domain.usecase

import com.sage.cahekr.cakehrandroidapp.data.repository.FilmRepositoryImpl
import com.sage.cahekr.cakehrandroidapp.domain.DomainFixtures
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.amshove.kluent.shouldBeEqualTo
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.net.UnknownHostException

@RunWith(JUnit4::class)
class GetFilmListUseCaseTest {

    @MockK
    internal lateinit var mockFilmRepository: FilmRepositoryImpl

    private lateinit var useCase: GetFilmListUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        useCase = GetFilmListUseCase(mockFilmRepository)
    }

    @Test
    fun `return list of films`() {
        val films = listOf(DomainFixtures.getFilm(), DomainFixtures.getFilm())
        coEvery { mockFilmRepository.getFilms() } returns films

        val result = runBlocking { useCase.execute() }

        result shouldBeEqualTo GetFilmListUseCase.Result.Success(films)
    }

    @Test
    fun `return error when repository throws an exception`() {
        val exception = UnknownHostException()
        coEvery { mockFilmRepository.getFilms() } throws exception

        val result = runBlocking { useCase.execute() }

        result shouldBeEqualTo GetFilmListUseCase.Result.Error(exception)
    }
}