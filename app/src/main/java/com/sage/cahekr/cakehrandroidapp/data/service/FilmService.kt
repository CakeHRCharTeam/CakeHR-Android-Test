package com.sage.cahekr.cakehrandroidapp.data.service

import retrofit2.http.GET

internal interface FilmService {

    @GET("films")
    suspend fun films(): FilmResponse
}
