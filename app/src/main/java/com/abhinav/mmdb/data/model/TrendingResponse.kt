package com.abhinav.mmdb.data.model


import com.google.gson.annotations.SerializedName

data class TrendingItem(
    @SerializedName("gender")
    val gender: Int = 0,
    @SerializedName("known_for_department")
    val knownForDepartment: String = "",
    @SerializedName("known_for")
    val knownFor: List<KnownFor>?,
    @SerializedName("popularity")
    val popularity: Double = 0.0,
    @SerializedName("name")
    val name: String = "",
    @SerializedName("profile_path")
    val profilePath: String = "",
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("adult")
    val adult: Boolean = false
)


data class KnownFor(
    @SerializedName("overview")
    val overview: String = "",
    @SerializedName("original_language")
    val originalLanguage: String = "",
    @SerializedName("original_title")
    val originalTitle: String = "",
    @SerializedName("video")
    val video: Boolean = false,
    @SerializedName("title")
    val title: String = "",
    @SerializedName("genre_ids")
    val genreIds: List<Int>?,
    @SerializedName("poster_path")
    val posterPath: String = "",
    @SerializedName("backdrop_path")
    val backdropPath: String = "",
    @SerializedName("release_date")
    val releaseDate: String = "",
    @SerializedName("media_type")
    val mediaType: String = "",
    @SerializedName("vote_average")
    val voteAverage: Double = 0.0,
    @SerializedName("popularity")
    val popularity: Double = 0.0,
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("adult")
    val adult: Boolean = false,
    @SerializedName("vote_count")
    val voteCount: Int = 0
)


data class TrendingResponse(
    @SerializedName("page")
    val page: Int = 0,
    @SerializedName("total_pages")
    val totalPages: Int = 0,
    @SerializedName("results")
    val results: List<TrendingItem>?,
    @SerializedName("total_results")
    val totalResults: Int = 0
)


