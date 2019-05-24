package com.abhinav.mmdb.data.repo

import com.abhinav.mmdb.data.api.ApiInterface
import com.abhinav.mmdb.data.model.Configurations
import com.abhinav.mmdb.data.model.Result
import com.abhinav.mmdb.data.model.TrendingResponse

class HomeRepository : BaseRepository() {

    suspend fun fetchConfigurations(): Result<Configurations> {
        return safeApiCall(
            { ApiInterface.getAPIService().fetchConfigurations().await() },
            "Unable to fetch basic configurations"
        )
    }

    suspend fun getTrending(): Result<TrendingResponse> {
        return safeApiCall(
            { ApiInterface.getAPIService().getTrending(mediaType = "all", timeWindow = "week", pageNo = 1).await() },
            "Unable to fetch trending items"
        )
    }
}