package com.sage.cahekr.cakehrandroidapp.network;

import com.sage.cahekr.cakehrandroidapp.model.FilmResults;

import retrofit2.Call;
import retrofit2.http.GET;

public interface FilmService {
    @GET("films")
    Call<FilmResults> getFilms();
}