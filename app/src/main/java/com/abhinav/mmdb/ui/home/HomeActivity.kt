package com.abhinav.mmdb.ui.home

import android.os.Bundle
import androidx.fragment.app.commit
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.abhinav.mmdb.R
import com.abhinav.mmdb.ui.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

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
        viewModel.failureLiveData.observe(this, Observer { onFailure(it) })
        viewModel.fetchConfigurations()
    }

    private fun showHomeFragment() {
        val fragment = HomeFragment.getInstance()
        supportFragmentManager.commit {
            addToBackStack(fragment::class.java.simpleName)
            add(frame_container.id, fragment, fragment::class.java.simpleName)
        }
    }
}
