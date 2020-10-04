package com.sage.cahekr.cakehrandroidapp.network;

import com.sage.cahekr.cakehrandroidapp.model.Film;

import retrofit2.Call;
import retrofit2.http.GET;

public interface SWService {
    @GET("films")
    Call<Film> getFilms();
}