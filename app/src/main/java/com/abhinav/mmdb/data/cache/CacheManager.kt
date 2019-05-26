package com.abhinav.mmdb.data.cache

import com.abhinav.mmdb.data.model.Configurations
import com.abhinav.mmdb.data.model.GenresItem

object CacheManager {
    var genreMap: Map<Int, String>? = null
    var genreList: List<GenresItem>? = null
    var configurations: Configurations? = null

}