package com.abhinav.mmdb.data.model


import com.google.gson.annotations.SerializedName

data class TrendingItem(
    @SerializedName("gender")
    val gender: Int = 0,
    @SerializedName("known_for_department")
    val knownForDepartment: String = "",
    @SerializedName("known_for")
    val knownFor: List<TrendingItem>?,
    @SerializedName("popularity")
    val popularity: Double = 0.0,
    @SerializedName("name")
    val name: String = "",
    @SerializedName("profile_path")
    val profilePath: String = "",
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


/*
*
* {
  "page": 1,
  "results": [
    {
      "backdrop_path": "/qsD5OHqW7DSnaQ2afwz8Ptht1Xb.jpg",
      "first_air_date": "2011-04-17",
      "genre_ids": [
        10765,
        18,
        10759
      ],
      "id": 1399,
      "name": "Game of Thrones",
      "origin_country": [
        "US"
      ],
      "original_language": "en",
      "original_name": "Game of Thrones",
      "overview": "Seven noble families fight for control of the mythical land of Westeros. Friction between the houses leads to full-scale war. All while a very ancient evil awakens in the farthest north. Amidst the war, a neglected military order of misfits, the Night's Watch, is all that stands between the realms of men and icy horrors beyond.",
      "poster_path": "/u3bZgnGQ9T01sWNhyveQz0wH0Hl.jpg",
      "vote_average": 8.1,
      "vote_count": 5945,
      "popularity": 386.81
    },
    {
      "id": 299534,
      "video": false,
      "vote_count": 5912,
      "vote_average": 8.5,
      "title": "Avengers: Endgame",
      "release_date": "2019-04-24",
      "original_language": "en",
      "original_title": "Avengers: Endgame",
      "genre_ids": [
        28,
        12,
        878
      ],
      "backdrop_path": "/7RyHsO4yDXtBv1zUU3mTpHeQ0d5.jpg",
      "adult": false,
      "overview": "After the devastating events of Avengers: Infinity War, the universe is in ruins due to the efforts of the Mad Titan, Thanos. With the help of remaining allies, the Avengers must assemble once more in order to undo Thanos' actions and restore order to the universe once and for all, no matter what consequences may be in store.",
      "poster_path": "/or06FN3Dka5tukK1e9sl16pB3iy.jpg",
      "popularity": 262.342
    },
    {
      "adult": false,
      "gender": 2,
      "name": "Jason Statham",
      "id": 976,
      "known_for": [
        {
          "adult": false,
          "backdrop_path": "/qjfE7SkPXpqFs8FX8rIaG6eO2aK.jpg",
          "genre_ids": [
            28,
            53,
            80
          ],
          "id": 82992,
          "original_language": "en",
          "original_title": "Fast & Furious 6",
          "overview": "Hobbs has Dominic and Brian reassemble their crew to take down a team of mercenaries: Dominic unexpectedly gets convoluted also facing his presumed deceased girlfriend, Letty.",
          "poster_path": "/b9gTJKLdSbwcQRKzmqMq3dMfRwI.jpg",
          "release_date": "2013-05-21",
          "title": "Fast & Furious 6",
          "video": false,
          "vote_average": 6.8,
          "vote_count": 7013,
          "popularity": 2.077,
          "media_type": "movie"
        },
        {
          "adult": false,
          "backdrop_path": "/ypyeMfKydpyuuTMdp36rMlkGDUL.jpg",
          "genre_ids": [
            28,
            80,
            53
          ],
          "id": 168259,
          "original_language": "en",
          "original_title": "Furious 7",
          "overview": "Deckard Shaw seeks revenge against Dominic Toretto and his family for his comatose brother.",
          "poster_path": "/dCgm7efXDmiABSdWDHBDBx2jwmn.jpg",
          "release_date": "2015-04-01",
          "title": "Furious 7",
          "video": false,
          "vote_average": 7.3,
          "vote_count": 6467,
          "popularity": 23.326,
          "media_type": "movie"
        },
        {
          "adult": false,
          "backdrop_path": "/jzdnhRhG0dsuYorwvSqPqqnM1cV.jpg",
          "genre_ids": [
            28,
            80,
            53
          ],
          "id": 337339,
          "original_language": "en",
          "original_title": "The Fate of the Furious",
          "overview": "When a mysterious woman seduces Dom into the world of crime and a betrayal of those closest to him, the crew face trials that will test them as never before.",
          "poster_path": "/dImWM7GJqryWJO9LHa3XQ8DD5NH.jpg",
          "release_date": "2017-04-12",
          "title": "The Fate of the Furious",
          "video": false,
          "vote_average": 6.9,
          "vote_count": 6268,
          "popularity": 33.646,
          "media_type": "movie"
        }
      ],
      "known_for_department": "Acting",
      "profile_path": "/PhWiWgasncGWD9LdbsGcmxkV4r.jpg",
      "popularity": 27.69
    }
  ],
  "total_pages": 1000,
  "total_results": 20000
}
*
* */


