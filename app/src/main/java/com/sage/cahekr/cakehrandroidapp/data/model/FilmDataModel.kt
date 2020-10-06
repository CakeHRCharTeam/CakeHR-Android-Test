package com.sage.cahekr.cakehrandroidapp.data.model

import com.google.gson.annotations.SerializedName
import com.sage.cahekr.cakehrandroidapp.domain.model.FilmDomainModel

data class FilmDataModel(

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

internal fun FilmDataModel.toDomainModel(): FilmDomainModel {
    return FilmDomainModel(
        title = this.title,
        director = this.director,
        created = this.created,
        url = this.url
    )
}
