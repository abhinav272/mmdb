package com.abhinav.mmdb.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.abhinav.mmdb.R
import com.abhinav.mmdb.ui.BaseFragment

class HomeFragment: BaseFragment() {

    companion object{
        fun getInstance() = HomeFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

}
