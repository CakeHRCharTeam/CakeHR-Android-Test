package com.sage.cahekr.cakehrandroidapp.data.model

import com.sage.cahekr.cakehrandroidapp.data.DataFixtures
import com.sage.cahekr.cakehrandroidapp.domain.model.FilmDomainModel
import org.amshove.kluent.shouldBeEqualTo
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class FilmDataModelTest {

    @Test
    fun `data module should map expected fields`() {

        val film = DataFixtures.getFilm()

        val domainModel = film.toDomainModel()

        domainModel shouldBeEqualTo FilmDomainModel(
            film.title,
            film.director,
            film.created,
            film.url
        )
    }
}