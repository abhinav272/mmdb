package com.abhinav.mmdb.data.repo

import com.abhinav.mmdb.data.api.ApiInterface
import com.abhinav.mmdb.data.model.Configurations
import com.abhinav.mmdb.data.model.Result

class HomeRepository: BaseRepository() {

    suspend fun fetchConfigurations(): Result<Configurations> {
        return safeApiCall({ApiInterface.getAPIService().fetchConfigurations().await()},
            "Unable to fetch basic configurations")
    }
}