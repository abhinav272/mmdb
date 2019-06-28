package com.abhinav.mmdb.ui.home

import android.os.Bundle
import android.transition.TransitionInflater
import android.util.Log
import android.view.View
import androidx.fragment.app.commit
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.abhinav.mmdb.R
import com.abhinav.mmdb.data.model.NowPlaying
import com.abhinav.mmdb.ui.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

class HomeActivity : BaseActivity(), HomeFragment.HomeFragmentHost {

    private lateinit var viewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViewModel()
        initViewModelListener()
        showHomeFragment()
    }

    private fun initViewModelListener() {
        viewModel.homeTitleLiveData.observe(this, Observer {
            it?.takeIf { event -> !event.isAlreadyHandled }?.let { eventName ->
                title = eventName.getContent()
            }
        })
    }

    override fun onNowPlayingSelected(
        nowPlaying: NowPlaying,
        position: Int,
        view: View,
        sharedViewList: ArrayList<View>
    ) {
        showExploreNowPlayingFragment(nowPlaying, position, view, sharedViewList)
    }

    private fun showExploreNowPlayingFragment(
        nowPlaying: NowPlaying,
        position: Int,
        view: View,
        sharedViewList: ArrayList<View>
    ) {
        val fragment = ExploreNowPlayingFragment.getInstance(nowPlaying, position)
        supportFragmentManager.apply {
            findFragmentByTag(HomeFragment::class.java.simpleName)?.let {
                it.sharedElementReturnTransition = TransitionInflater.from(it.context).inflateTransition(R.transition.default_transition)
                it.exitTransition = TransitionInflater.from(it.context).inflateTransition(android.R.transition.no_transition)
            }
        }.commit {
            sharedViewList.forEach {
                addSharedElement(it, it.transitionName)
                Log.e("Sending", "${it.transitionName} for ${it.id}")
            }
            replace(frame_container.id, fragment, fragment::class.java.simpleName)
            addToBackStack(fragment::class.java.simpleName)
        }
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        viewModel.failureLiveData.observe(this, Observer { onFailure(it) })
        viewModel.fetchConfigurations()
    }

    private fun showHomeFragment() {
        val fragment = HomeFragment.getInstance()
        supportFragmentManager.commit {
            add(frame_container.id, fragment, fragment::class.java.simpleName)
        }
    }
}
