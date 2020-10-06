package com.sage.cahekr.cakehrandroidapp.data

import com.sage.cahekr.cakehrandroidapp.data.model.FilmDataModel

object DataFixtures {

    internal fun getFilm(
        title: String = "Pulp Fiction",
        director: String = "Quentin Tarantino",
        created: String = "1994",
        url: String = "https://www.filmaffinity.com/es/film160882.html"
    ): FilmDataModel =
        FilmDataModel(title = title, director = director, created = created, url = url)
}
