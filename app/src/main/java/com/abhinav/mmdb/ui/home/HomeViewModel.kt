package com.abhinav.mmdb.ui.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.abhinav.mmdb.data.cache.CacheManager
import com.abhinav.mmdb.data.model.*
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
    var trendingLiveData = MutableLiveData<List<TrendingItem>>()
    var nowPlayingLiveData = MutableLiveData<List<NowPlaying>>()
    var homeTitleLiveData = MutableLiveData<Event<String>>()

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

    fun updateTitle(title: String){
        homeTitleLiveData.value = Event(title)
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

        ioScope.launch {
            when (val genreResult = repo.getGenreMasterData()) {
                is Result.Success<GenreResponse> -> {
                    CacheManager.genreMap = genreResult.data.genres?.associateBy({ it.id }, { it.name })
                }
                is Result.Failure -> {
                    failureLiveData.postValue(Event(genreResult))
                    Log.e(TAG, genreResult.message)
                }
                else -> {
                    genreResult as Result.Error
                    genreResult.exception.message?.let { Log.e(TAG, it, genreResult.exception) }
                }
            }
        }
    }

    fun fetchTrendingItems() {
        ioScope.launch {
            when (val trendingResult = repo.getTrending()) {
                is Result.Success -> {
                    trendingLiveData.postValue(trendingResult.data.results)
                }

                is Result.Failure -> {
                    failureLiveData.postValue(Event(trendingResult))
                    Log.e(TAG, trendingResult.message)
                }

                else -> {
                    trendingResult as Result.Error
                    trendingResult.exception.message?.let { Log.e(TAG, it, trendingResult.exception) }
                }
            }
        }
    }

    fun fetchNowPlaying() {
        ioScope.launch {
            when (val nowPlayingResult = repo.getNowPlayingMovies()) {
                is Result.Success -> {
                    nowPlayingLiveData.postValue(nowPlayingResult.data.results)
                }

                is Result.Failure -> {
                    failureLiveData.postValue(Event(nowPlayingResult))
                    Log.e(TAG, nowPlayingResult.message)
                }

                else -> {
                    nowPlayingResult as Result.Error
                    nowPlayingResult.exception.message?.let { Log.e(TAG, it, nowPlayingResult.exception) }
                }
            }
        }
    }
}
