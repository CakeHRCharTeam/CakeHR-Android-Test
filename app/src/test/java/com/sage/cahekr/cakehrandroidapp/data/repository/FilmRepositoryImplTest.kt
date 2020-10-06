package com.sage.cahekr.cakehrandroidapp.data.repository

import com.sage.cahekr.cakehrandroidapp.data.DataFixtures
import com.sage.cahekr.cakehrandroidapp.data.model.toDomainModel
import com.sage.cahekr.cakehrandroidapp.data.service.FilmResponse
import com.sage.cahekr.cakehrandroidapp.data.service.FilmService
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.amshove.kluent.shouldBeEqualTo
import org.junit.Before
import org.junit.Test

class FilmRepositoryImplTest {

    @MockK
    internal lateinit var mockService: FilmService

    private lateinit var repositoryImpl: FilmRepositoryImpl

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        repositoryImpl = FilmRepositoryImpl(mockService)
    }

    @Test
    fun `getFilms and maps to Model`() {
        coEvery {
            mockService.films()
        } returns FilmResponse(results = listOf(DataFixtures.getFilm()))

        val result = runBlocking { repositoryImpl.getFilms() }.first()

        result shouldBeEqualTo DataFixtures.getFilm().toDomainModel()
    }

    @Test
    fun `getFilms returns empty if response is empty`() {
        coEvery {
            mockService.films()
        } returns FilmResponse(null, listOf())

        val result = runBlocking { repositoryImpl.getFilms() }

        result shouldBeEqualTo emptyList()
    }
}