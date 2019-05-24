package com.abhinav.mmdb.ui.home

import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.abhinav.mmdb.R
import com.abhinav.mmdb.data.cache.CacheManager
import com.abhinav.mmdb.data.model.Configurations
import com.abhinav.mmdb.data.model.Result
import com.abhinav.mmdb.data.repo.HomeRepository
import com.abhinav.mmdb.showToastMsg
import com.abhinav.mmdb.ui.BaseActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeActivity : BaseActivity() {

    private lateinit var viewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViewModel()
        showHomeFragment()
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
    }

    private fun showHomeFragment() {
        var repo = HomeRepository()

        GlobalScope.launch {
            val fetchConfigurations = repo.fetchConfigurations()

            withContext(Dispatchers.Main) {
                when (fetchConfigurations) {
                    is Result.Success<Configurations> -> {
                        CacheManager.configurations = fetchConfigurations.data
                    }
                    else -> {
                        fetchConfigurations as Result.Error
                        fetchConfigurations.exception.message?.let { showToastMsg(it) }
                    }
                }
            }
        }
    }
}
