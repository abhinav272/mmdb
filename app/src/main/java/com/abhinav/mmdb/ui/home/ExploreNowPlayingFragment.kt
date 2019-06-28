package com.abhinav.mmdb.ui.home

import android.os.Bundle
import android.transition.Fade
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.doOnPreDraw
import androidx.core.view.postDelayed
import androidx.fragment.app.activityViewModels
import com.abhinav.mmdb.R
import com.abhinav.mmdb.data.model.NowPlaying
import com.abhinav.mmdb.inflate
import com.abhinav.mmdb.ui.BaseFragment
import com.abhinav.mmdb.ui.adapters.ExploreNowPlayingViewPagerAdapter
import com.abhinav.mmdb.utils.transformer.DepthTransformation
import kotlinx.android.synthetic.main.fragment_explore_now_playing.*

class ExploreNowPlayingFragment : BaseFragment() {

    private lateinit var viewPagerAdapter: ExploreNowPlayingViewPagerAdapter
    private val viewModel by activityViewModels<HomeViewModel>()

    companion object {
        private const val POSITION = "position"
        private const val MOVIE_ID = "movie_id"

        fun getInstance(nowPlaying: NowPlaying, position: Int): ExploreNowPlayingFragment {
            return ExploreNowPlayingFragment().apply {
                arguments = bundleOf(
                    POSITION to position,
                    MOVIE_ID to nowPlaying.id
                )
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        postponeEnterTransition()
        sharedElementReturnTransition = TransitionInflater.from(context).inflateTransition(R.transition.default_transition)
        sharedElementEnterTransition =
            TransitionInflater.from(context).inflateTransition(R.transition.default_transition)
        viewPagerAdapter = ExploreNowPlayingViewPagerAdapter(this, viewModel.nowPlayingLiveData.value!!)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return container?.inflate(R.layout.fragment_explore_now_playing)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view_pager.apply {
            adapter = viewPagerAdapter
            setPageTransformer(true, DepthTransformation())
            currentItem = arguments?.get(POSITION) as Int
        }
    }

}
