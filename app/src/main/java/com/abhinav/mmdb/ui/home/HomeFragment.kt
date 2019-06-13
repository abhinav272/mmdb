package com.abhinav.mmdb.ui.home

//import com.abhinav.mmdb.ui.adapters.TrendingItemsAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.abhinav.mmdb.R
import com.abhinav.mmdb.inflate
import com.abhinav.mmdb.ui.BaseFragment
import com.abhinav.mmdb.ui.adapters.HomeItemsAdapter
import com.abhinav.mmdb.utils.ItemOffsetDecoration
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : BaseFragment() {

    private lateinit var gridLayoutManager: GridLayoutManager
    private lateinit var homeItemsAdapter: HomeItemsAdapter
    private val viewModel by activityViewModels<HomeViewModel>()

    companion object {
        fun getInstance() = HomeFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeItemsAdapter = HomeItemsAdapter()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return container?.inflate(R.layout.fragment_home)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindTrendingAdapter()

        viewModel.trendingLiveData.observe(this, Observer {
            homeItemsAdapter.onTrendingItemsLoaded(it)
        })

        viewModel.nowPlayingLiveData.observe(this, Observer {
            homeItemsAdapter.onNowPlayingItemsLoaded(it)
        })

        viewModel.fetchTrendingItems()
        viewModel.fetchNowPlaying()
    }

    private fun bindTrendingAdapter() {
        gridLayoutManager = GridLayoutManager(rv_items.context, 3)
        gridLayoutManager.apply {
            spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int = when (homeItemsAdapter.getItemViewType(position)) {
                    homeItemsAdapter.TRENDING_ITEMS -> 3
                    else -> 1
                }
            }
        }

        rv_items.apply {
            adapter = homeItemsAdapter
            layoutManager = gridLayoutManager
            addItemDecoration(ItemOffsetDecoration(10))
        }
    }

}
