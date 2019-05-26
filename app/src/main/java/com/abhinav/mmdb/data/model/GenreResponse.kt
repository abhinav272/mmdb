package com.abhinav.mmdb.data.model


import com.google.gson.annotations.SerializedName

data class GenreResponse(@SerializedName("genres")
                         val genres: List<GenresItem>?)


data class GenresItem(@SerializedName("name")
                      val name: String = "",
                      @SerializedName("id")
                      val id: Int = 0)


