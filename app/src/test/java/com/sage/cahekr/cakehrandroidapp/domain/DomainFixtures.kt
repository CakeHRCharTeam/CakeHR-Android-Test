package com.sage.cahekr.cakehrandroidapp.domain

import com.sage.cahekr.cakehrandroidapp.domain.model.FilmDomainModel

object DomainFixtures {

    internal fun getFilm(
        title: String = "Pulp Fiction",
        director: String = "Quentin Tarantino",
        created: String = "1994",
        url: String = "https://www.filmaffinity.com/es/film160882.html"
    ): FilmDomainModel =
        FilmDomainModel(title = title, director = director, created = created, url = url)
}
