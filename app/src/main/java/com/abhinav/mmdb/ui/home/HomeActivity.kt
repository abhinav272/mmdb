package com.abhinav.mmdb.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.abhinav.mmdb.R
import com.abhinav.mmdb.ui.home.HomeViewModel
import kotlinx.android.synthetic.main.activity_main.*

class HomeActivity : AppCompatActivity() {

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

    }
}
