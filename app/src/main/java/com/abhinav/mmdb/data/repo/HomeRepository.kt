package com.abhinav.mmdb.data.repo

import com.abhinav.mmdb.data.api.ApiInterface
import com.abhinav.mmdb.data.model.*

class HomeRepository : BaseRepository() {

    suspend fun fetchConfigurations(): Result<Configurations> {
        return safeApiCall(
            { ApiInterface.getAPIService().fetchConfigurations().await() },
            "Unable to fetch basic configurations"
        )
    }

    suspend fun getGenreMasterData(): Result<GenreResponse> {
        return safeApiCall(
            { ApiInterface.getAPIService().getGenreMasterData().await() },
            "Unable to fetch Genres"
        )
    }

    suspend fun getTrending(): Result<TrendingResponse> {
        return safeApiCall(
            { ApiInterface.getAPIService().getTrending(mediaType = "all", timeWindow = "week", pageNo = 1).await() },
            "Unable to fetch trending items"
        )
    }

    suspend fun getNowPlayingMovies(): Result<NowPlayingResponse> {
        return safeApiCall(
            {ApiInterface.getAPIService().getNowPlayingMovies(pageNo = 1, language = "en").await()},
            "Unable to fetch Now Playing movies"
        )
    }
}