package com.sage.cahekr.cakehrandroidapp.data.service

import com.google.gson.annotations.SerializedName
import com.sage.cahekr.cakehrandroidapp.data.model.FilmDataModel

data class FilmResponse(

	@SerializedName("count")
	val count: Int? = null,

	@SerializedName("results")
	val results: List<FilmDataModel>
)
