package com.abhinav.mmdb.ui.home

import android.animation.LayoutTransition
import android.os.Bundle
import android.transition.Fade
import android.transition.Transition
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.OvershootInterpolator
import androidx.core.os.bundleOf
import androidx.core.view.doOnPreDraw
import androidx.core.view.postDelayed
import androidx.fragment.app.activityViewModels
import androidx.viewpager.widget.ViewPager
import com.abhinav.mmdb.R
import com.abhinav.mmdb.data.cache.CacheManager
import com.abhinav.mmdb.data.model.NowPlaying
import com.abhinav.mmdb.inflate
import com.abhinav.mmdb.ui.BaseFragment
import com.abhinav.mmdb.ui.adapters.ExploreNowPlayingViewPagerAdapter
import com.abhinav.mmdb.utils.transformer.DepthTransformation
import kotlinx.android.synthetic.main.fragment_explore_now_playing.*
import kotlinx.android.synthetic.main.layout_trending_item.view.*

class ExploreNowPlayingFragment : BaseFragment() {

    private lateinit var list: List<NowPlaying>
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
        list = viewModel.nowPlayingLiveData.value!!
        postponeEnterTransition()
        sharedElementReturnTransition = TransitionInflater.from(context).inflateTransition(R.transition.default_transition)
        sharedElementEnterTransition =
            TransitionInflater.from(context).inflateTransition(R.transition.default_transition)
        viewPagerAdapter = ExploreNowPlayingViewPagerAdapter(this, list)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return container?.inflate(R.layout.fragment_explore_now_playing)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateAnimation()
        view_pager.apply {
            adapter = viewPagerAdapter
            setPageTransformer(true, DepthTransformation())
            currentItem = arguments?.get(POSITION) as Int
            postDelayed(450){
                updateMovieDetails(currentItem)
            }
        }

        view_pager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
                updateMovieDetails(position)
            }

        })
    }

    private fun updateAnimation() {
//        container.layoutTransition.apply {
//            enableTransitionType(LayoutTransition.CHANGING)
//        }
    }

    private fun updateMovieDetails(position: Int) {

        container.apply {
            alpha = 0f
            translationY = 70f
            animate()
                .setInterpolator(OvershootInterpolator())
                .translationY(0f)
                .alpha(1f)
                .duration = 450
        }


        tv_movie_name.text = list[position].originalTitle
        tv_movie_rating.text = String.format("Rating: %.1f", list[position].voteAverage)
        tv_movie_genre.text = ""
        var textGen = ""
        list[position].genreIds?.take(3)?.forEach {
            val genre = String.format("%s|", CacheManager.genreMap?.get(it))
            textGen = String.format("%s%s", textGen, genre)
        }
        tv_movie_genre.text = textGen.dropLast(1)
    }

}
