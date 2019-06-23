package com.abhinav.mmdb.ui.home

//import com.abhinav.mmdb.ui.adapters.TrendingItemsAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.abhinav.mmdb.R
import com.abhinav.mmdb.inflate
import com.abhinav.mmdb.ui.BaseFragment
import com.abhinav.mmdb.ui.adapters.NowPlayingItemsAdapter
import com.abhinav.mmdb.ui.adapters.TrendingItemsAdapter
import com.abhinav.mmdb.utils.ItemOffsetDecoration
import com.abhinav.mmdb.utils.StartSnapHelper
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : BaseFragment() {

    private lateinit var trendingItemsAdapter: TrendingItemsAdapter
    private lateinit var gridLayoutManager: GridLayoutManager
    private lateinit var nowPlayingItemsAdapter: NowPlayingItemsAdapter
    private val viewModel by activityViewModels<HomeViewModel>()

    companion object {
        fun getInstance() = HomeFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        trendingItemsAdapter = TrendingItemsAdapter()
        nowPlayingItemsAdapter = NowPlayingItemsAdapter()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return container?.inflate(R.layout.fragment_home)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindTrendingAdapter()
        bindNowPlayingAdapter()

        viewModel.updateTitle(getString(R.string.app_name))

        viewModel.trendingLiveData.observe(this, Observer {
            trendingItemsAdapter.updateItems(it)
        })

        viewModel.nowPlayingLiveData.observe(this, Observer {
            nowPlayingItemsAdapter.updateItems(it)
        })

        viewModel.fetchTrendingItems()
        viewModel.fetchNowPlaying()
    }

//    private fun bindTrendingAdapter() {
//        gridLayoutManager = GridLayoutManager(rv_items.context, 3)
//        gridLayoutManager.apply {
//            spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
//                override fun getSpanSize(position: Int): Int = when (homeItemsAdapter.getItemViewType(position)) {
//                    homeItemsAdapter.TRENDING_ITEMS -> 3
//                    else -> 1
//                }
//            }
//        }
//
//        rv_items.apply {
//            adapter = homeItemsAdapter
//            layoutManager = gridLayoutManager
//            addItemDecoration(ItemOffsetDecoration(10))
//        }
//    }

    private fun bindTrendingAdapter() {
        rv_trending_items.apply {
            layoutManager = LinearLayoutManager(rv_trending_items.context, LinearLayout.HORIZONTAL, false)
            adapter = trendingItemsAdapter
            onFlingListener = null
        }

        val snapHelper = StartSnapHelper()
        snapHelper.attachToRecyclerView(rv_trending_items)
    }

    private fun bindNowPlayingAdapter() {
        rv_now_playing_items.apply {
            layoutManager = GridLayoutManager(rv_now_playing_items.context, 2)
            adapter = nowPlayingItemsAdapter
            addItemDecoration(ItemOffsetDecoration(10))
        }
    }
}
