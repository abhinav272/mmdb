package com.abhinav.mmdb.ui.home

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.abhinav.mmdb.data.cache.CacheManager
import com.abhinav.mmdb.data.model.Configurations
import com.abhinav.mmdb.data.model.Result
import com.abhinav.mmdb.data.repo.HomeRepository
import com.abhinav.mmdb.ui.BaseViewModel
import com.abhinav.mmdb.utils.Event
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class HomeViewModel : BaseViewModel() {

    private val TAG = "HomeViewModel"

    private val repo = HomeRepository()

    /**
     * This is the job for all coroutines started by this ViewModel.
     * Cancelling this job will cancel all coroutines started by this ViewModel.
     */
    private val viewModelJob = SupervisorJob()

    /**
     * This is the main scope for all coroutines launched by MainViewModel.
     * Since we pass viewModelJob, you can cancel all coroutines
     * launched by uiScope by calling viewModelJob.cancel()
     */
    private val ioScope = CoroutineScope(Dispatchers.IO + viewModelJob)

    /**
     * Cancel all coroutines when the ViewModel is cleared
     */
    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    fun fetchConfigurations() {
        ioScope.launch {
            when (val fetchConfigurations = repo.fetchConfigurations()) {
                is Result.Success<Configurations> -> {
                    CacheManager.configurations = fetchConfigurations.data
                }
                is Result.Failure -> {
                    failureLiveData.postValue(Event(fetchConfigurations))
                    Log.e(TAG, fetchConfigurations.message)
                }
                else -> {
                    fetchConfigurations as Result.Error
                    fetchConfigurations.exception.message?.let { Log.e(TAG, it, fetchConfigurations.exception) }
                }
            }
        }
    }
}
