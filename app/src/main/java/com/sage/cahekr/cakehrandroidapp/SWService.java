package com.sage.cahekr.cakehrandroidapp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface SWService {
    @GET("films")
    Call<Film> getFilms();
}