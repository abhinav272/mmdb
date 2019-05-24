package com.abhinav.mmdb.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.abhinav.mmdb.R
import com.abhinav.mmdb.ui.BaseFragment

class HomeFragment: BaseFragment() {

    val viewModel by activityViewModels<HomeViewModel>()

    companion object{
        fun getInstance() = HomeFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.trendingLiveData.observe(this, Observer { it.forEach { it1 -> it1.name.let { s -> println(s) } } })
        viewModel.fetchTrendingItems()
    }

}
