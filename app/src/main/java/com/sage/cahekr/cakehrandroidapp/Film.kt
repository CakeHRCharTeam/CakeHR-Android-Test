package com.sage.cahekr.cakehrandroidapp

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Film(

	@SerializedName("count")
	val count: Int? = null,

	@SerializedName("results")
	val results: List<ResultsItem>? = null
)

data class ResultsItem(

	@SerializedName("edited")
	val edited: String? = null,

	@SerializedName("director")
	val director: String? = null,

	@SerializedName("created")
	val created: String? = null,

	@SerializedName("vehicles")
	val vehicles: List<String?>? = null,

	@SerializedName("opening_crawl")
	val openingCrawl: String? = null,

	@SerializedName("title")
	val title: String,

	@SerializedName("url")
	val url: String? = null,

	@SerializedName("characters")
	val characters: List<String?>? = null,

	@SerializedName("episode_id")
	val episodeId: Int? = null,

	@SerializedName("planets")
	val planets: List<String?>? = null,

	@SerializedName("release_date")
	val releaseDate: String? = null,

	@SerializedName("starships")
	val starships: List<String?>? = null,

	@SerializedName("species")
	val species: List<String?>? = null,

	@SerializedName("producer")
	val producer: String? = null
)
